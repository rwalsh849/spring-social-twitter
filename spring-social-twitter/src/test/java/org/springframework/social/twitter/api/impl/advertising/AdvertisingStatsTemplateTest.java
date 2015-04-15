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
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.springframework.social.twitter.api.advertising.StatisticalGranularity;
import org.springframework.social.twitter.api.advertising.StatisticalMetric;
import org.springframework.social.twitter.api.advertising.StatisticalSnapshot;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;

/**
 * @author Hudson mendes
 */
public class AdvertisingStatsTemplateTest extends AbstractTwitterApiTest {
	private static final String DEFAULT_ENCODING = "utf-8";
	private static final MathContext DEFAULT_ROUNDER = MathContext.DECIMAL32;
	
	@Test
	public void byCampaigns() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId1 = "92ph";
		String mockedCampaignId2 = "x902";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns" +
				"?campaign_ids=" + URLEncoder.encode(mockedCampaignId1 + "," + mockedCampaignId2, DEFAULT_ENCODING) +
				"&granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", DEFAULT_ENCODING) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", DEFAULT_ENCODING) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", DEFAULT_ENCODING)))
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
				new String[] { mockedCampaignId1, mockedCampaignId2 },
				new StatisticalMetric[] { StatisticalMetric.billed_follows });
	}
	
	@Test
	public void byCampaign() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId = "92ph";
		mockServer
			.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns" +
				"/" + mockedCampaignId +
				"?granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows,promoted_account_follow_rate,billed_charge_local_micro,mobile_conversion_rated", DEFAULT_ENCODING) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", DEFAULT_ENCODING) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", DEFAULT_ENCODING)))
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
	
	@Test
	public void byFundingInstruments() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedFundingInstrument1 = "92ph";
		String mockedFundingInstrument2 = "x902";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/funding_instruments" +
					"?funding_instrument_ids=" + URLEncoder.encode(mockedFundingInstrument1 + "," + mockedFundingInstrument2, DEFAULT_ENCODING) +
					"&granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", DEFAULT_ENCODING) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", DEFAULT_ENCODING) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", DEFAULT_ENCODING)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("stats-by-campaigns"), APPLICATION_JSON));
	
		List<StatisticalSnapshot> campaigns = twitter.advertisingStatsOperations().byFundingInstruments(
				mockedAccountId,
				new StatisticalSnapshotQueryingDataBuilder()
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00))
					.withGranularity(StatisticalGranularity.HOUR)
					.withFundingInstruments(mockedFundingInstrument1, mockedFundingInstrument2)
					.withStatisticalMetric(StatisticalMetric.billed_follows));
		
		assertCampaignContents(
				campaigns,
				new String[] { mockedFundingInstrument1, mockedFundingInstrument2 },
				new StatisticalMetric[] { StatisticalMetric.billed_follows });
	}
	
	private void assertCampaignContents(List<StatisticalSnapshot> snapshots, String[] checkingIds, StatisticalMetric[] checkingMetrics) {
		assertEquals(checkingIds.length, snapshots.size());
		
		for (int i = 0; i < checkingIds.length; i++) 
			assertEquals(checkingIds[i], snapshots.get(i).getId());
		
		snapshots.forEach(snapshot -> {
			for (int i = 0; i < checkingMetrics.length; i++) 
				assertNotNull(snapshot.getMetric(checkingMetrics[i]));
		});
	}
	
	private void assertCampaignSingleContents(StatisticalSnapshot snapshot, String checkingId) {
		assertEquals(checkingId, snapshot.getId());
		
		assertNotNull(snapshot.getMetric(StatisticalMetric.billed_follows));
		assertThat(
				snapshot.getMetric(StatisticalMetric.billed_follows).entries(),
				hasItems(new Integer[] {13, 998}));
		
		assertNotNull(snapshot.getMetric(StatisticalMetric.promoted_account_follow_rate));
		assertThat(
				snapshot.getMetric(StatisticalMetric.promoted_account_follow_rate).entries(),
				hasItems(new Double[] {15.3}));
		
		assertNotNull(snapshot.getMetric(StatisticalMetric.billed_charge_local_micro));
		assertThat(
				snapshot.getMetric(StatisticalMetric.billed_charge_local_micro).entries(),
				hasItems(new BigDecimal[] { new BigDecimal(1.5).round(DEFAULT_ROUNDER), new BigDecimal(1.76).round(DEFAULT_ROUNDER), new BigDecimal(9.999999).round(DEFAULT_ROUNDER) }));
	}
}
