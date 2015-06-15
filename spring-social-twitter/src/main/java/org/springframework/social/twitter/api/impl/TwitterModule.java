/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl;

import org.springframework.social.twitter.api.AccountSettings;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Entities;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.OEmbedTweet;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.SavedSearch;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.SuggestionCategory;
import org.springframework.social.twitter.api.Trend;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UrlEntity;
import org.springframework.social.twitter.api.UserList;
import org.springframework.social.twitter.api.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.advertising.Campaign;
import org.springframework.social.twitter.api.advertising.FundingInstrument;
import org.springframework.social.twitter.api.advertising.GlobalOptOut;
import org.springframework.social.twitter.api.advertising.LineItem;
import org.springframework.social.twitter.api.advertising.StatisticsSnapshot;
import org.springframework.social.twitter.api.advertising.TailoredAudience;
import org.springframework.social.twitter.api.advertising.TailoredAudienceChange;
import org.springframework.social.twitter.api.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.impl.advertising.AdvertisingAccountMixin;
import org.springframework.social.twitter.api.impl.advertising.CampaignMixin;
import org.springframework.social.twitter.api.impl.advertising.FundingInstrumentMixin;
import org.springframework.social.twitter.api.impl.advertising.GlobalOptOutMixin;
import org.springframework.social.twitter.api.impl.advertising.LineItemMixin;
import org.springframework.social.twitter.api.impl.advertising.StatisticsSnapshotMixin;
import org.springframework.social.twitter.api.impl.advertising.TailoredAudienceChangeMixin;
import org.springframework.social.twitter.api.impl.advertising.TailoredAudienceMixin;
import org.springframework.social.twitter.api.impl.advertising.TargetingCriteriaMixin;

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
        context.setMixInAnnotations(StatisticsSnapshot.class, StatisticsSnapshotMixin.class);
        context.setMixInAnnotations(TailoredAudience.class, TailoredAudienceMixin.class);
        context.setMixInAnnotations(GlobalOptOut.class, GlobalOptOutMixin.class);
        context.setMixInAnnotations(TailoredAudience.class, TailoredAudienceMixin.class);
        context.setMixInAnnotations(TailoredAudienceChange.class, TailoredAudienceChangeMixin.class);

        context.setMixInAnnotations(AccountSettings.class, AccountSettingsMixin.class);
        context.setMixInAnnotations(AccountSettings.TimeZone.class, AccountSettingsMixin.TimeZoneMixin.class);
        context.setMixInAnnotations(AccountSettings.SleepTime.class, AccountSettingsMixin.SleepTimeMixin.class);
        context.setMixInAnnotations(AccountSettings.TrendLocation.class, AccountSettingsMixin.TrendLocationMixin.class);

        context.setMixInAnnotations(OEmbedTweet.class, OEmbedTweetMixin.class);
    }

}
