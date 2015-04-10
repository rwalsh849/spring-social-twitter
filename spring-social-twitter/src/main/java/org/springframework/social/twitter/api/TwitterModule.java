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
package org.springframework.social.twitter.api;

import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.domain.models.advertising.Campaign;
import org.springframework.social.twitter.api.domain.models.advertising.FundingInstrument;
import org.springframework.social.twitter.api.domain.models.advertising.LineItem;
import org.springframework.social.twitter.api.domain.models.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.domain.models.standard.AccountSettings;
import org.springframework.social.twitter.api.domain.models.standard.DirectMessage;
import org.springframework.social.twitter.api.domain.models.standard.Entities;
import org.springframework.social.twitter.api.domain.models.standard.HashTagEntity;
import org.springframework.social.twitter.api.impl.advertising.mixins.AdvertisingAccountMixin;
import org.springframework.social.twitter.api.impl.advertising.mixins.CampaignMixin;
import org.springframework.social.twitter.api.impl.advertising.mixins.FundingInstrumentMixin;
import org.springframework.social.twitter.api.impl.advertising.mixins.LineItemMixin;
import org.springframework.social.twitter.api.impl.advertising.mixins.TargetingCriteriaMixin;
import org.springframework.social.twitter.api.impl.standard.holders.UserList;
import org.springframework.social.twitter.api.impl.standard.mixins.AccountSettingsMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.DirectMessageMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.EntitiesMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.HashTagEntityMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.MediaEntityMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.MentionEntityMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.OEmbedTweetMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.PlaceMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.SavedSearchMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.SearchResultsMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.SimilarPlacesMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.SuggestionCategoryMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.TrendMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.TrendsMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.TweetMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.TwitterProfileMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.UrlEntityMixin;
import org.springframework.social.twitter.api.impl.standard.mixins.UserListMixin;
import org.springframework.social.twitter.api.impl.standard.models.MediaEntity;
import org.springframework.social.twitter.api.impl.standard.models.MentionEntity;
import org.springframework.social.twitter.api.impl.standard.models.OEmbedTweet;
import org.springframework.social.twitter.api.impl.standard.models.Place;
import org.springframework.social.twitter.api.impl.standard.models.SavedSearch;
import org.springframework.social.twitter.api.impl.standard.models.SearchResults;
import org.springframework.social.twitter.api.impl.standard.models.SimilarPlacesResponse;
import org.springframework.social.twitter.api.impl.standard.models.SuggestionCategory;
import org.springframework.social.twitter.api.impl.standard.models.Trend;
import org.springframework.social.twitter.api.impl.standard.models.Trends;
import org.springframework.social.twitter.api.impl.standard.models.Tweet;
import org.springframework.social.twitter.api.impl.standard.models.TwitterProfile;
import org.springframework.social.twitter.api.impl.standard.models.UrlEntity;

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
		
		context.setMixInAnnotations(AccountSettings.class, AccountSettingsMixin.class);
		context.setMixInAnnotations(AccountSettings.TimeZone.class, AccountSettingsMixin.TimeZoneMixin.class);
		context.setMixInAnnotations(AccountSettings.SleepTime.class, AccountSettingsMixin.SleepTimeMixin.class);
		context.setMixInAnnotations(AccountSettings.TrendLocation.class, AccountSettingsMixin.TrendLocationMixin.class);
		
		context.setMixInAnnotations(OEmbedTweet.class, OEmbedTweetMixin.class);
	}

}
