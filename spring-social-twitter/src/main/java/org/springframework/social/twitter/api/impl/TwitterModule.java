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

import org.springframework.social.twitter.api.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.advertising.Campaign;
import org.springframework.social.twitter.api.advertising.FundingInstrument;
import org.springframework.social.twitter.api.advertising.LineItem;
import org.springframework.social.twitter.api.advertising.StatsSnapshot;
import org.springframework.social.twitter.api.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.basic.AccountSettings;
import org.springframework.social.twitter.api.basic.DirectMessage;
import org.springframework.social.twitter.api.basic.Entities;
import org.springframework.social.twitter.api.basic.HashTagEntity;
import org.springframework.social.twitter.api.impl.advertising.AdvertisingAccountMixin;
import org.springframework.social.twitter.api.impl.advertising.CampaignMixin;
import org.springframework.social.twitter.api.impl.advertising.FundingInstrumentMixin;
import org.springframework.social.twitter.api.impl.advertising.LineItemMixin;
import org.springframework.social.twitter.api.impl.advertising.StatsSnapshotMixin;
import org.springframework.social.twitter.api.impl.advertising.TargetingCriteriaMixin;
import org.springframework.social.twitter.api.impl.basic.AccountSettingsMixin;
import org.springframework.social.twitter.api.impl.basic.DirectMessageMixin;
import org.springframework.social.twitter.api.impl.basic.EntitiesMixin;
import org.springframework.social.twitter.api.impl.basic.HashTagEntityMixin;
import org.springframework.social.twitter.api.impl.basic.MediaEntity;
import org.springframework.social.twitter.api.impl.basic.MediaEntityMixin;
import org.springframework.social.twitter.api.impl.basic.MentionEntity;
import org.springframework.social.twitter.api.impl.basic.MentionEntityMixin;
import org.springframework.social.twitter.api.impl.basic.OEmbedTweet;
import org.springframework.social.twitter.api.impl.basic.OEmbedTweetMixin;
import org.springframework.social.twitter.api.impl.basic.Place;
import org.springframework.social.twitter.api.impl.basic.PlaceMixin;
import org.springframework.social.twitter.api.impl.basic.SavedSearch;
import org.springframework.social.twitter.api.impl.basic.SavedSearchMixin;
import org.springframework.social.twitter.api.impl.basic.SearchResults;
import org.springframework.social.twitter.api.impl.basic.SearchResultsMixin;
import org.springframework.social.twitter.api.impl.basic.SimilarPlacesMixin;
import org.springframework.social.twitter.api.impl.basic.SimilarPlacesResponse;
import org.springframework.social.twitter.api.impl.basic.SuggestionCategory;
import org.springframework.social.twitter.api.impl.basic.SuggestionCategoryMixin;
import org.springframework.social.twitter.api.impl.basic.Trend;
import org.springframework.social.twitter.api.impl.basic.TrendMixin;
import org.springframework.social.twitter.api.impl.basic.Trends;
import org.springframework.social.twitter.api.impl.basic.TrendsMixin;
import org.springframework.social.twitter.api.impl.basic.Tweet;
import org.springframework.social.twitter.api.impl.basic.TweetMixin;
import org.springframework.social.twitter.api.impl.basic.TwitterProfile;
import org.springframework.social.twitter.api.impl.basic.TwitterProfileMixin;
import org.springframework.social.twitter.api.impl.basic.UrlEntity;
import org.springframework.social.twitter.api.impl.basic.UrlEntityMixin;
import org.springframework.social.twitter.api.impl.basic.UserList;
import org.springframework.social.twitter.api.impl.basic.UserListMixin;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Jackson module for registering mixin annotations against Twitter model classes.
 */
@SuppressWarnings("serial")
public class TwitterModule extends SimpleModule {

	public TwitterModule() {
		super("TwitterModule");
	}
	
	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(TwitterProfile.class, TwitterProfileMixin.class);
		context.setMixInAnnotations(SavedSearch.class, SavedSearchMixin.class);
		context.setMixInAnnotations(Trend.class, TrendMixin.class);
		context.setMixInAnnotations(Trends.class, TrendsMixin.class);
		context.setMixInAnnotations(SuggestionCategory.class, SuggestionCategoryMixin.class);
		context.setMixInAnnotations(DirectMessage.class, DirectMessageMixin.class);
		context.setMixInAnnotations(UserList.class, UserListMixin.class);
		context.setMixInAnnotations(Tweet.class, TweetMixin.class);
		context.setMixInAnnotations(SearchResults.class, SearchResultsMixin.class);
		context.setMixInAnnotations(Place.class, PlaceMixin.class);
		context.setMixInAnnotations(SimilarPlacesResponse.class, SimilarPlacesMixin.class);
		context.setMixInAnnotations(Entities.class, EntitiesMixin.class);
		context.setMixInAnnotations(HashTagEntity.class, HashTagEntityMixin.class);
		context.setMixInAnnotations(MediaEntity.class, MediaEntityMixin.class);
		context.setMixInAnnotations(MentionEntity.class, MentionEntityMixin.class);
		context.setMixInAnnotations(UrlEntity.class, UrlEntityMixin.class);
		
		context.setMixInAnnotations(AdvertisingAccount.class, AdvertisingAccountMixin.class);
		context.setMixInAnnotations(FundingInstrument.class, FundingInstrumentMixin.class);
		context.setMixInAnnotations(LineItem.class, LineItemMixin.class);
		context.setMixInAnnotations(Campaign.class, CampaignMixin.class);
		context.setMixInAnnotations(TargetingCriteria.class, TargetingCriteriaMixin.class);
		context.setMixInAnnotations(StatsSnapshot.class, StatsSnapshotMixin.class);
		
		context.setMixInAnnotations(AccountSettings.class, AccountSettingsMixin.class);
		context.setMixInAnnotations(AccountSettings.TimeZone.class, AccountSettingsMixin.TimeZoneMixin.class);
		context.setMixInAnnotations(AccountSettings.SleepTime.class, AccountSettingsMixin.SleepTimeMixin.class);
		context.setMixInAnnotations(AccountSettings.TrendLocation.class, AccountSettingsMixin.TrendLocationMixin.class);
		
		context.setMixInAnnotations(OEmbedTweet.class, OEmbedTweetMixin.class);
	}

}
