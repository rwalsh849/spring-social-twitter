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
package org.springframework.social.twitter.api.impl.advertising;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.springframework.social.twitter.api.domain.models.advertising.StatisticalGranularity;
import org.springframework.social.twitter.api.domain.models.advertising.StatisticalMetric;
import org.springframework.social.twitter.api.domain.models.advertising.StatisticalSnapshot;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;
import org.springframework.social.twitter.api.impl.advertising.builders.StatisticalSnapshotQueryingDataBuilder;

/**
 * @author Hudson mendes
 */
public class AdvertisingStatsTemplateTest extends AbstractTwitterApiTest {
	@Test
	public void byCampaign() {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns?campaign_ids=" + mockedCampaignId + "&granularity=DAY&metrics=billed_follows"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("stats-by-campaigns"), APPLICATION_JSON));
	
		StatisticalSnapshot campaign = twitter.advertisingStatsOperations().byCampaign(
				mockedAccountId,
				new StatisticalSnapshotQueryingDataBuilder()
					.withGranularity(StatisticalGranularity.DAY)
					.withCampaigns(mockedCampaignId)
					.withStatisticalMetric(StatisticalMetric.billed_follows));
		
		assertCampaignContents(campaign);
	}
	
	private void assertCampaignContents(StatisticalSnapshot snapshot) {
		assertEquals(LocalDateTime.of(2013, Month.APRIL, 16, 07, 00, 00), snapshot.getEndTime());
		assertEquals(LocalDateTime.of(2013, Month.APRIL, 13, 07, 00, 00), snapshot.getStartTime());
		assertEquals(StatisticalGranularity.DAY, snapshot.getGranularity());
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.estimated_charge_local_micro).entries(),
				IsIterableContainingInOrder.contains(new BigDecimal(22.00), new BigDecimal(22.00), new BigDecimal(22.00)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.billed_charge_local_micro).entries(),
				IsIterableContainingInOrder.contains(new BigDecimal(22.00), new BigDecimal(22.00), new BigDecimal(22.00)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.billed_engagements).entries(),
				IsIterableContainingInOrder.contains(new Integer(59), new Integer(50), new Integer(69)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.billed_follows).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_account_follows).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_account_impressions).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_account_profile_visits).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_clicks).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_engagements).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_follows).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_impressions).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_replies).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_retweets).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_clicks).entries(),
				IsIterableContainingInOrder.contains(new Integer(65), new Integer(75), new Integer(81)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_engagements).entries(),
				IsIterableContainingInOrder.contains(new Integer(65), new Integer(75), new Integer(81)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_follows).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_impressions).entries(),
				IsIterableContainingInOrder.contains(new Integer(851), new Integer(875), new Integer(1187)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_replies).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_retweets).entries(),
				IsIterableContainingInOrder.contains(new Integer(0), new Integer(0), new Integer(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_timeline_engagement_rate).entries(),
				IsIterableContainingInOrder.contains(new Double(0.0763807285546416), new Double(0.0857142857142857), new Double(0.0682392586352148)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_tweet_search_engagement_rate).entries(),
				IsIterableContainingInOrder.contains(new Double(0), new Double(0), new Double(0)));
		
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_account_follow_rate).entries(),
				IsIterableContainingInOrder.contains(new Double(0), new Double(0), new Double(0)));
	}
	
}
