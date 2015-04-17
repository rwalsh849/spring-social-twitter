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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.springframework.social.twitter.api.advertising.StatisticsGranularity;
import org.springframework.social.twitter.api.advertising.StatisticsMetric;
import org.springframework.social.twitter.api.advertising.StatisticsSnapshot;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;

/**
 * @author Hudson mendes
 */
public class AdvertisingStatsTemplateTest extends AbstractTwitterApiTest {
	@Test
	public void byAccounts() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + 
				"?granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> snapshots = twitter.statisticsOperations().byAccounts(
				mockedAccountId,
				new StatisticsOfAccountQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(snapshots);
	}
	
	@Test
	public void byCampaigns() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId1 = "92ph";
		String mockedCampaignId2 = "x902";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/campaigns" +
				"?campaign_ids=" + URLEncoder.encode(mockedCampaignId1 + "," + mockedCampaignId2, UTF8) +
				"&granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> snapshots = twitter.statisticsOperations().byCampaigns(
				mockedAccountId,
				new StatisticsOfCampaignQueryBuilder()
					.withCampaigns(mockedCampaignId1, mockedCampaignId2)
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(snapshots);
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
				"&metrics=" + URLEncoder.encode("billed_follows,promoted_account_follow_rate,billed_charge_local_micro,mobile_conversion_rated", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot-single"), APPLICATION_JSON));
	
		StatisticsSnapshot snapshot = twitter.statisticsOperations().byCampaign(
				mockedAccountId,
				mockedCampaignId,
				new StatisticsOfCampaignQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(
							StatisticsMetric.billed_follows,
							StatisticsMetric.promoted_account_follow_rate,
							StatisticsMetric.billed_charge_local_micro,
							StatisticsMetric.mobile_conversion_rated)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotSingleContents(snapshot);
	}
	
	@Test
	public void byFundingInstruments() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedFundingInstrument1 = "92ph";
		String mockedFundingInstrument2 = "x902";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/funding_instruments" +
					"?funding_instrument_ids=" + URLEncoder.encode(mockedFundingInstrument1 + "," + mockedFundingInstrument2, UTF8) +
					"&granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> campaigns = twitter.statisticsOperations().byFundingInstruments(
				mockedAccountId,
				new StatisticsOfFundingInstrumentQueryBuilder()
					.withFundingInstruments(mockedFundingInstrument1, mockedFundingInstrument2)
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(campaigns);
	}
	
	@Test
	public void byFundingInstrument() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedFundingInstrumentId = "92ph";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/funding_instruments" +
					"/" + mockedFundingInstrumentId +
					"?granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot-single"), APPLICATION_JSON));
	
		StatisticsSnapshot snapshot = twitter.statisticsOperations().byFundingInstrument(
				mockedAccountId,
				mockedFundingInstrumentId,
				new StatisticsOfFundingInstrumentQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotSingleContents(snapshot);
	}
	
	@Test
	public void byLineItems() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedLineItemId1 = "92ph";
		String mockedLineItemId2 = "x902";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/line_items" +
				"?line_item_ids=" + URLEncoder.encode(mockedLineItemId1 + "," + mockedLineItemId2, UTF8) +
				"&granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> snapshots = twitter.statisticsOperations().byLineItems(
				mockedAccountId,
				new StatisticsOfLineItemQueryBuilder()
					.withLineItems(mockedLineItemId1, mockedLineItemId2)
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeFrom(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00))
					.activeUntil(LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(snapshots);
	}
	
	@Test
	public void byLineItem() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedLineItemId = "92ph";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/line_items" +
					"/" + mockedLineItemId +
					"?granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot-single"), APPLICATION_JSON));
	
		StatisticsSnapshot snapshot = twitter.statisticsOperations().byLineItem(
				mockedAccountId,
				mockedLineItemId,
				new StatisticsOfLineItemQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotSingleContents(snapshot);
	}
	
	@Test
	public void byPromotedAccounts() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedPromotedAccountId1 = "92ph";
		String mockedPromotedAccountId2 = "x902";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/promoted_accounts" +
				"?promoted_account_ids=" + URLEncoder.encode(mockedPromotedAccountId1 + "," + mockedPromotedAccountId2, UTF8) +
				"&granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> snapshots = twitter.statisticsOperations().byPromotedAccounts(
				mockedAccountId,
				new StatisticsOfPromotedAccountQueryBuilder()
					.withPromotedAccounts(mockedPromotedAccountId1, mockedPromotedAccountId2)
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeFrom(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00))
					.activeUntil(LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(snapshots);
	}
	
	@Test
	public void byPromotedAccount() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedPromotedAccountId = "92ph";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/promoted_accounts" +
					"/" + mockedPromotedAccountId +
					"?granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot-single"), APPLICATION_JSON));
	
		StatisticsSnapshot snapshot = twitter.statisticsOperations().byPromotedAccount(
				mockedAccountId,
				mockedPromotedAccountId,
				new StatisticsOfPromotedAccountQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotSingleContents(snapshot);
	}
	
	@Test
	public void byPromotedTweets() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedPromotedTweetId1 = "92ph";
		String mockedPromotedTweetId2 = "x902";
		mockServer
		.expect(requestTo(
				"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/promoted_tweets" +
				"?promoted_tweet_ids=" + URLEncoder.encode(mockedPromotedTweetId1 + "," + mockedPromotedTweetId2, UTF8) +
				"&granularity=HOUR" +
				"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
				"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
				"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot"), APPLICATION_JSON));
	
		List<StatisticsSnapshot> snapshots = twitter.statisticsOperations().byPromotedTweets(
				mockedAccountId,
				new StatisticsOfPromotedTweetQueryBuilder()
					.withPromotedTweets(mockedPromotedTweetId1, mockedPromotedTweetId2)
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeFrom(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00))
					.activeUntil(LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotContents(snapshots);
	}
	
	@Test
	public void byPromotedTweet() throws UnsupportedEncodingException {
		String mockedAccountId = "0ga0yn";
		String mockedPromotedTweetId = "92ph";
		
		mockServer
			.expect(requestTo(
					"https://ads-api.twitter.com/0/stats/accounts/" + mockedAccountId + "/promoted_tweets" +
					"/" + mockedPromotedTweetId +
					"?granularity=HOUR" +
					"&metrics=" + URLEncoder.encode("billed_follows", UTF8) +
					"&start_time=" + URLEncoder.encode("2015-03-06T07:00:00Z", UTF8) +
					"&end_time=" + URLEncoder.encode("2015-03-13T07:00:00Z", UTF8)))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("statistics-snapshot-single"), APPLICATION_JSON));
	
		StatisticsSnapshot snapshot = twitter.statisticsOperations().byPromotedTweet(
				mockedAccountId,
				mockedPromotedTweetId,
				new StatisticsOfPromotedTweetQueryBuilder()
					.withGranularity(StatisticsGranularity.HOUR)
					.withStatisticalMetric(StatisticsMetric.billed_follows)
					.activeBetween(LocalDateTime.of(2015, Month.MARCH, 06, 07, 00, 00), LocalDateTime.of(2015, Month.MARCH, 13, 07, 00, 00)));
		
		assertSnapshotSingleContents(snapshot);
	}
	
	private void assertSnapshotContents(List<StatisticsSnapshot> snapshots) {
		assertEquals(2, snapshots.size());
		
		assertEquals("92ph", snapshots.get(0).getId());
		assertNotNull(snapshots.get(0).getMetric(StatisticsMetric.billed_follows));
		
		assertEquals("x902", snapshots.get(1).getId());
		assertNotNull(snapshots.get(1).getMetric(StatisticsMetric.billed_follows));
	}
	
	private void assertSnapshotSingleContents(StatisticsSnapshot snapshot) {
		assertEquals("92ph", snapshot.getId());
		
		assertNotNull(snapshot.getMetric(StatisticsMetric.billed_follows));
		assertThat(
				snapshot.getMetric(StatisticsMetric.billed_follows).entries(),
				hasItems(new Integer[] {13, 998}));
		
		assertNotNull(snapshot.getMetric(StatisticsMetric.promoted_account_follow_rate));
		assertThat(
				snapshot.getMetric(StatisticsMetric.promoted_account_follow_rate).entries(),
				hasItems(new Double[] {15.3}));
		
		assertNotNull(snapshot.getMetric(StatisticsMetric.billed_charge_local_micro));
		assertThat(
				snapshot.getMetric(StatisticsMetric.billed_charge_local_micro).entries(),
				hasItems(new BigDecimal[] {
						new BigDecimal(1.5).round(ROUNDER),
						new BigDecimal(1.76).round(ROUNDER),
						new BigDecimal(9.999999).round(ROUNDER) }));
	}
}
