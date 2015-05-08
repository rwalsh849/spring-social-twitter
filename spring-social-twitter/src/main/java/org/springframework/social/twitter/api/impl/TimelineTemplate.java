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

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.social.twitter.api.OEmbedOptions;
import org.springframework.social.twitter.api.OEmbedTweet;
import org.springframework.social.twitter.api.StatusDetails;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link TimelineOperations}, providing a binding to Twitter's tweet and timeline-oriented REST resources.
 * @author Craig Walls
 */
public class TimelineTemplate extends AbstractTwitterOperations implements TimelineOperations {
	private static final MultiValueMap<String, Object> EMPTY_DATA = new LinkedMultiValueMap<String, Object>();
	
	private final RestTemplate restTemplate;

	public TimelineTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}

	public List<Tweet> getHomeTimeline() {
		return getHomeTimeline(20, 0, 0);
	}

	public List<Tweet> getHomeTimeline(int pageSize) {
		return getHomeTimeline(pageSize, 0, 0);
	}
	
	public List<Tweet> getHomeTimeline(int pageSize, long sinceId, long maxId) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_HOME_TIMELINE)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}
	
	public List<Tweet> getUserTimeline() {
		return getUserTimeline(20, 0, 0);
	}

	public List<Tweet> getUserTimeline(int pageSize) {
		return getUserTimeline(pageSize, 0, 0);
	}

	public List<Tweet> getUserTimeline(int pageSize, long sinceId, long maxId) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_USER_TIMELINE)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public List<Tweet> getUserTimeline(String screenName) {
		return getUserTimeline(screenName, 20, 0, 0);
	}

	public List<Tweet> getUserTimeline(String screenName, int pageSize) {
		return getUserTimeline(screenName, pageSize, 0, 0);
	}

	public List<Tweet> getUserTimeline(String screenName, int pageSize, long sinceId, long maxId) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("screen_name", screenName);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_USER_TIMELINE)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public List<Tweet> getUserTimeline(long userId) {
		return getUserTimeline(userId, 20, 0, 0);
	}

	public List<Tweet> getUserTimeline(long userId, int pageSize) {
		return getUserTimeline(userId, pageSize, 0, 0);
	}

	public List<Tweet> getUserTimeline(long userId, int pageSize, long sinceId, long maxId) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("user_id", String.valueOf(userId));
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_USER_TIMELINE)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public List<Tweet> getMentions() {
		return getMentions(20, 0, 0);
	}

	public List<Tweet> getMentions(int pageSize) {
		return getMentions(pageSize, 0, 0);
	}

	public List<Tweet> getMentions(int pageSize, long sinceId, long maxId) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, sinceId, maxId);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_MENTIONS_TIMELINE)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}
	
	public List<Tweet> getRetweetsOfMe() {
		return getRetweetsOfMe(1, 20, 0, 0);
	}

	public List<Tweet> getRetweetsOfMe(int page, int pageSize) {
		return getRetweetsOfMe(page, pageSize, 0, 0);
	}

	public List<Tweet> getRetweetsOfMe(int page, int pageSize, long sinceId, long maxId) {
		requireUserAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_RETWEETS_OF_ME)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public Tweet getStatus(long tweetId) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_SHOW)
					.withArgument("tweet_id", tweetId)
					.withArgument(parameters)
					.build(),
				Tweet.class);
	}
	
	public OEmbedTweet getStatusOEmbed(long tweetId) {
		return getStatusOEmbed(tweetId, new OEmbedOptions());
	}
	
	public OEmbedTweet getStatusOEmbed(long tweetId, OEmbedOptions options) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = options.toRequestParameters();
		parameters.set("id", String.valueOf(tweetId));
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_OEMBEDED)
					.withArgument(parameters)
					.build(),
				OEmbedTweet.class);
	}

	
	public Tweet updateStatus(String message) {
		return updateStatus(new TweetData(message));
	}

	public Tweet updateStatus(String message, Resource media) {
		return updateStatus(new TweetData(message).withMedia(media));
	}

	@SuppressWarnings("deprecation")
	public Tweet updateStatus(String message, StatusDetails details) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_UPDATE)
					.build(),
				new RestRequestBodyBuilder()
					.withField("status", message)
					.withFields(details.toParameterMap())
					.build(),
				Tweet.class);
	}

	public Tweet updateStatus(String message, Resource media, StatusDetails details) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_UPDATE_WITH_MEDIA)
					.build(),
				new RestRequestBodyBuilder()
					.withField("status", message)
					.withField("media", media)
					.withFields(details.toParameterMap())
					.build(),
				Tweet.class);
	}

	public Tweet updateStatus(TweetData tweetData) {
		requireUserAuthorization();
		
		TwitterApiUriResourceForStandard resourceUri = 
				tweetData.hasMedia() ? 
				TwitterApiUriResourceForStandard.STATUSES_UPDATE_WITH_MEDIA : 
				TwitterApiUriResourceForStandard.STATUSES_UPDATE;
						
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(resourceUri)
					.build(),
				tweetData.toRequestParameters(),
				Tweet.class);
	}
	
	public void deleteStatus(long tweetId) {
		requireUserAuthorization();
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_DESTROY)
					.withArgument("tweet_id", tweetId)
					.build(),
				EMPTY_DATA,
				String.class);
	}

	public Tweet retweet(long tweetId) {
		requireUserAuthorization();
		return restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_RETWEET)
					.withArgument("tweet_id", tweetId)
					.build(),
				EMPTY_DATA,
				Tweet.class);
	}

	public List<Tweet> getRetweets(long tweetId) {
		return getRetweets(tweetId, 100);
	}

	public List<Tweet> getRetweets(long tweetId, int count) {
		requireEitherUserOrAppAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.STATUSES_RETWEETS)
					.withArgument("tweet_id", tweetId)
					.withArgument("count", String.valueOf(count))
					.withArgument("include_entities", "true")
					.build(),
				TweetList.class);
	}

	public List<Tweet> getFavorites() {
		return getFavorites(20);
	}

	public List<Tweet> getFavorites(int pageSize) {
		requireEitherUserOrAppAuthorization();

		// Note: The documentation for /favorites.json doesn't list the count parameter, but it works anyway.
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, 0, 0);
		parameters.set("include_entities", "true");
				
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.FAVORITES_LIST)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}

	public List<Tweet> getFavorites(long userId) {
		return getFavorites(userId, 20);
	}

	public List<Tweet> getFavorites(long userId, int pageSize) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, 0, 0);
		parameters.set("user_id", String.valueOf(userId));
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.FAVORITES_LIST)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}
	
	public List<Tweet> getFavorites(String screenName) {
		return getFavorites(screenName, 20);
	}

	public List<Tweet> getFavorites(String screenName, int pageSize) {
		requireEitherUserOrAppAuthorization();
		
		MultiValueMap<String, Object> parameters = PagingUtils.buildPagingParametersWithCount(pageSize, 0, 0);
		parameters.set("screen_name", screenName);
		parameters.set("include_entities", "true");
		
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.FAVORITES_LIST)
					.withArgument(parameters)
					.build(),
				TweetList.class);
	}
	
	public void addToFavorites(long tweetId) {
		requireUserAuthorization();
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.FAVORITES_CREATE)
					.build(),
				new RestRequestBodyBuilder()
					.withField("id", String.valueOf(tweetId))
					.build(),
				String.class);
	}

	public void removeFromFavorites(long tweetId) {
		requireUserAuthorization();
		restTemplate.postForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForStandard.FAVORITES_DESTROY)
					.build(),
				new RestRequestBodyBuilder()
					.withField("id", String.valueOf(tweetId))
					.build(),
				String.class);
	}

	@SuppressWarnings("serial")
	private static class TweetList extends ArrayList<Tweet> {}
	
}
