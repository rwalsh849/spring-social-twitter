/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.ListOperations;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link ListOperations}, providing a binding to Twitter's list-oriented REST resources.
 * @author Craig Walls
 */
public class ListTemplate extends AbstractTwitterTemplate implements ListOperations {
	
	private final RestTemplate restTemplate;
					
	public ListTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	public List<UserList> getLists() {
		requireUserAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS)
					.build(),
				UserSubscriptionList.class);
	}
	
	public List<UserList> getLists(long userId) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS)
					.withArgument("user_id", String.valueOf(userId))
					.build(),
				UserSubscriptionList.class);
	}
	
	public List<UserList> getLists(String screenName) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS)
					.withArgument("screen_name", String.valueOf(screenName))
					.build(),
				UserSubscriptionList.class);
	}

	public UserList getList(long listId) {
		requireUserAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SHOW)
					.withArgument("list_id", String.valueOf(listId))
					.build(),
				UserList.class);
	}

	public UserList getList(String screenName, String listSlug) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SHOW)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.build(),
				UserList.class);
	}

	public List<Tweet> getListStatuses(long listId) {
		return getListStatuses(listId, 20, 0, 0);
	}

	public List<Tweet> getListStatuses(long listId, int pageSize) {
		return getListStatuses(listId, pageSize, 0, 0);
	}

	public List<Tweet> getListStatuses(long listId, int pageSize, long sinceId, long maxId) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("list_id", String.valueOf(listId));
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_STATUSES)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public List<Tweet> getListStatuses(String screenName, String listSlug) {
		return getListStatuses(screenName, listSlug, 20, 0, 0);
	}

	public List<Tweet> getListStatuses(String screenName, String listSlug, int pageSize) {
		return getListStatuses(screenName, listSlug, pageSize, 0, 0);
	}

	public List<Tweet> getListStatuses(String screenName, String listSlug, int pageSize, long sinceId, long maxId) {
		requireEitherUserOrAppAuthorization();

		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("owner_screen_name", screenName);
		parameters.set("slug", listSlug);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_STATUSES)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public UserList createList(String name, String description, boolean isPublic) {	
		requireUserAuthorization();
		
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_CREATE)
					.build(),
				buildListDataMap(name, description, isPublic),
				UserList.class);
	}

	public UserList updateList(long listId, String name, String description, boolean isPublic) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> bodyData = buildListDataMap(name, description, isPublic);
		bodyData.set("list_id", String.valueOf(listId));
		
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_UPDATE)
					.build(),
				bodyData,
				UserList.class);
	}

	public void deleteList(long listId) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> bodyData = new LinkedMultiValueMap<String, Object>();
		bodyData.set("list_id", String.valueOf(listId));
		
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_DESTROY)
					.build(),
				bodyData,
				UserList.class);
	}

	public CursoredList<TwitterProfile> getListMembers(long listId) {
		requireEitherUserOrAppAuthorization();
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS)
					.withArgument("list_id", String.valueOf(listId))
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}

	public CursoredList<TwitterProfile> getListMembersInCursor(long listId, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS)
					.withArgument("list_id", String.valueOf(listId))
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}

	public CursoredList<TwitterProfile> getListMembers(String screenName, String listSlug) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}

	public CursoredList<TwitterProfile> getListMembersInCursor(String screenName, String listSlug, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}

	public UserList addToList(long listId, long... newMemberIds) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_CREATE_ALL)
					.build(),
				new RestRequestBodyBuilder()
					.withField("user_id", ArrayUtils.join(newMemberIds))
					.withField("list_id", String.valueOf(listId))
					.build(),
				UserList.class);
	}

	public UserList addToList(long listId, String... newMemberScreenNames) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_CREATE_ALL)
					.build(),
				new RestRequestBodyBuilder()
					.withField("screen_name", ArrayUtils.join(newMemberScreenNames))
					.withField("list_id", String.valueOf(listId))
					.build(),
				UserList.class);
	}

	public void removeFromList(long listId, long memberId) {
		requireUserAuthorization();
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_DESTROY)
					.build(),
				new RestRequestBodyBuilder()
					.withField("user_id", String.valueOf(memberId))
					.withField("list_id", String.valueOf(listId))
					.build(),
				String.class);
	}

	public void removeFromList(long listId, String memberScreenName) {
		requireUserAuthorization();
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_DESTROY)
					.build(),
				new RestRequestBodyBuilder()
					.withField("screen_name", String.valueOf(memberScreenName))
					.withField("list_id", String.valueOf(listId))
					.build(),
				String.class);
	}

	public List<TwitterProfile> getListSubscribers(long listId) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS)
					.withArgument("list_id", String.valueOf(listId))
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}

	public List<TwitterProfile> getListSubscribers(String screenName, String listSlug) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.build(),
				TwitterProfileUsersList.class
			).getList();
	}
	
	public UserList subscribe(long listId) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_CREATE)
					.build(),
				new RestRequestBodyBuilder()
					.withField("list_id", String.valueOf(listId))
					.build(),
				UserList.class);
	}

	public UserList subscribe(String ownerScreenName, String listSlug) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_CREATE)
					.build(),
				new RestRequestBodyBuilder()
					.withField("owner_screen_name", ownerScreenName)
					.withField("slug", listSlug)
					.build(),
				UserList.class);
	}

	public UserList unsubscribe(long listId) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_DESTROY)
					.build(),
				new RestRequestBodyBuilder()
					.withField("list_id", String.valueOf(listId))
					.build(),
				UserList.class);
	}

	public UserList unsubscribe(String ownerScreenName, String listSlug) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_DESTROY)
					.build(),
				new RestRequestBodyBuilder()
					.withField("owner_screen_name", ownerScreenName)
					.withField("slug", listSlug)
					.build(),
				UserList.class);
	}

	public CursoredList<UserList> getMemberships(long userId) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERSHIPS)
					.withArgument("user_id", String.valueOf(userId))
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getMemberships(String screenName) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERSHIPS)
					.withArgument("screen_name", screenName)
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getMembershipsInCursor(long userId, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERSHIPS)
					.withArgument("user_id", String.valueOf(userId))
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getMembershipsInCursor(String screenName, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERSHIPS)
					.withArgument("screen_name", screenName)
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getSubscriptions(long userId) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIPTIONS)
					.withArgument("user_id", String.valueOf(userId))
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getSubscriptions(String screenName) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIPTIONS)
					.withArgument("screen_name", screenName)
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getSubscriptionsInCursor(long userId, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIPTIONS)
					.withArgument("user_id", String.valueOf(userId))
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				UserListList.class
			).getList();
	}

	public CursoredList<UserList> getSubscriptionsInCursor(String screenName, long cursor) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIPTIONS)
					.withArgument("screen_name", screenName)
					.withArgument("cursor", String.valueOf(cursor))
					.build(),
				UserListList.class
			).getList();
	}

	public boolean isMember(long listId, long memberId) {
		requireEitherUserOrAppAuthorization();
		return checkListConnection(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_SHOW)
					.withArgument("list_id", String.valueOf(listId))
					.withArgument("user_id", String.valueOf(memberId))
					.build());
	}

	public boolean isMember(String screenName, String listSlug, String memberScreenName) {
		requireEitherUserOrAppAuthorization();
		return checkListConnection(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_MEMBERS_SHOW)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.withArgument("screen_name", memberScreenName)
					.build());
	}

	public boolean isSubscriber(long listId, long subscriberId) {
		requireEitherUserOrAppAuthorization();
		return checkListConnection(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_SHOW)
					.withArgument("list_id", String.valueOf(listId))
					.withArgument("user_id", String.valueOf(subscriberId))
					.build());
	}

	public boolean isSubscriber(String screenName, String listSlug, String subscriberScreenName) {
		requireEitherUserOrAppAuthorization();
		return checkListConnection(
				new TwitterApiUriBuilder()
				.withResource(TwitterApiUriResourceForStandard.LISTS_SUBSCRIBERS_SHOW)
					.withArgument("owner_screen_name", screenName)
					.withArgument("slug", listSlug)
					.withArgument("screen_name", subscriberScreenName)
					.build());
	}

	// private helpers

	private boolean checkListConnection(URI uri) {
		try {
			restTemplate.getForObject(uri, String.class);
			return true;
		} catch (ResourceNotFoundException e) {
			return false;
		}
	}

	private MultiValueMap<String, Object> buildListDataMap(String name,
			String description, boolean isPublic) {
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<String, Object>();
		request.set("name", name);
		request.set("description", description);
		request.set("mode", isPublic ? "public" : "private");
		return request;
	}

	@SuppressWarnings("serial")
	private static class TweetList extends ArrayList<Tweet> {}
	
	@SuppressWarnings("serial")
	private static class UserSubscriptionList extends ArrayList<UserList> {}

}
