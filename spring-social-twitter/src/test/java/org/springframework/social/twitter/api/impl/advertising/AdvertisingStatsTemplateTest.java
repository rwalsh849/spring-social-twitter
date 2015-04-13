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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import junit.framework.Assert;

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
			.andRespond(withSuccess(jsonResource("ad-campaigns"), APPLICATION_JSON));
	
		StatisticalSnapshot campaign = twitter.advertisingStatsOperations().byCampaign(
				mockedAccountId,
				new StatisticalSnapshotQueryingDataBuilder()
					.withGranularity(StatisticalGranularity.DAY)
					.withCampaigns(mockedCampaignId)
					.withStatisticalMetric(StatisticalMetric.billed_follows));
		
		assertCampaignContents(campaign);
	}
	
	private void assertCampaignContents(StatisticalSnapshot snapshot) {
		Assert.fail();
	}
	
}
