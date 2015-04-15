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

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

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
	public void byCampaigns() {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId1 = "92ph";
		String mockedCampaignId2 = "x902";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns?campaign_ids=" + mockedCampaignId1 + "&campaign_ids=" + mockedCampaignId2 + "&granularity=HOUR&metrics=billed_follows&start_time=2015-03-06T07%3A00%3A00Z&end_time=2015-03-13T07%3A00%3A00Z"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("stats-by-campaigns"), APPLICATION_JSON));
	
		List<StatisticalSnapshot> campaigns = twitter.advertisingStatsOperations().byCampaigns(
				mockedAccountId,
				new StatisticalSnapshotQueryingDataBuilder()
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00))
					.withGranularity(StatisticalGranularity.HOUR)
					.withCampaigns(mockedCampaignId1, mockedCampaignId2)
					.withStatisticalMetric(StatisticalMetric.billed_follows));
		
		assertCampaignContents(
				campaigns,
				mockedCampaignId1, mockedCampaignId2);
	}
	
	@Test
	public void byCampaign() {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns/" + mockedCampaignId + "?granularity=HOUR&metrics=billed_follows&metrics=promoted_account_follow_rate&metrics=billed_charge_local_micro&metrics=mobile_conversion_rated&start_time=2015-03-06T07%3A00%3A00Z&end_time=2015-03-13T07%3A00%3A00Z"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("stats-by-campaigns-single"), APPLICATION_JSON));
	
		StatisticalSnapshot snapshot = twitter.advertisingStatsOperations().byCampaign(
				mockedAccountId,
				mockedCampaignId,
				new StatisticalSnapshotQueryingDataBuilder()
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00))
					.withGranularity(StatisticalGranularity.HOUR)
					.withStatisticalMetric(
							StatisticalMetric.billed_follows,
							StatisticalMetric.promoted_account_follow_rate,
							StatisticalMetric.billed_charge_local_micro,
							StatisticalMetric.mobile_conversion_rated));
		
		assertCampaignSingleContents(
				snapshot,
				mockedCampaignId);
	}
	
	private void assertCampaignContents(List<StatisticalSnapshot> snapshots, String... checkingIds) {
		assertEquals(checkingIds.length, snapshots.size());
		
		for (int i = 0; i < checkingIds.length; i++) 
			assertEquals(checkingIds[i], snapshots.get(i).getId());
	}
	
	private void assertCampaignSingleContents(StatisticalSnapshot snapshot, String checkingId) {
		assertEquals(checkingId, snapshot.getId());
	}
}
