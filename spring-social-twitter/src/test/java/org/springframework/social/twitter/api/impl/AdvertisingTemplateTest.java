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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingObjective;
import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingPlacementType;
import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingSentiment;
import org.springframework.social.twitter.api.domain.models.advertising.Campaign;
import org.springframework.social.twitter.api.domain.models.advertising.FundingInstrument;
import org.springframework.social.twitter.api.domain.models.advertising.FundingInstrumentType;
import org.springframework.social.twitter.api.domain.models.advertising.LineItem;
import org.springframework.social.twitter.api.domain.models.advertising.LineItemOptimization;
import org.springframework.social.twitter.api.domain.models.advertising.ReasonNotServable;
import org.springframework.social.twitter.api.domain.models.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.domain.models.advertising.TargetingType;
import org.springframework.social.twitter.api.domain.models.standard.ApprovalStatus;
import org.springframework.social.twitter.api.impl.advertising.builders.CampaignDataBuilder;
import org.springframework.social.twitter.api.impl.advertising.builders.LineItemDataBuilder;
import org.springframework.social.twitter.api.impl.advertising.builders.TargetingCriteriaDataBuilder;

/**
 * @author Hudson mendes
 */
public class AdvertisingTemplateTest extends AbstractTwitterApiTest {

	@Test
	public void getAccounts() {
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts?with_deleted=true"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-accounts"), APPLICATION_JSON));

		List<AdvertisingAccount> accounts = twitter.advertisingOperations().getAccounts();
		assertAdAccountContents(accounts);
	}

	@Test
	public void getCampaign() {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns/" + mockedCampaignId))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-campaigns-single"), APPLICATION_JSON));
	
		Campaign campaign = twitter.advertisingOperations().getCampaign(mockedAccountId, mockedCampaignId);
		assertSingleCampaignContents(campaign);
	}
	
	@Test
	public void getCampaigns() {
		String mockedAccountId = "0ga0yn";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns?with_deleted=true"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-campaigns"), APPLICATION_JSON));
	
		List<Campaign> campaigns = twitter.advertisingOperations().getCampaigns(mockedAccountId);
		assertCampaignContents(campaigns);
	}
	
	@Test
	public void createCampaign() throws UnsupportedEncodingException {
		String doesntMatterString = "doesn-matter";
		BigDecimal doesntMatterDecimal = new BigDecimal(1.00);
		LocalDateTime doesntMatterDate = LocalDateTime.now();
		Boolean doesntMatterBool = false; 
		String mockedAccountId = "1ga1yn";
		
		String chainedPostContent = 
				"name=" + doesntMatterString + "&" +
				"currency=" + doesntMatterString + "&" +
				"funding_instrument_id=" + doesntMatterString + "&" +
				"total_budget_amount_local_micro=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"daily_budget_amount_local_micro=" + doesntMatterDecimal.add(new BigDecimal(15)).multiply(new BigDecimal(1000000L)) + "&" +
				"start_time=" + URLEncoder.encode(doesntMatterDate.toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"end_time=" + URLEncoder.encode(doesntMatterDate.plusDays(1).toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"standard_delivery=" + doesntMatterBool + "&" +
				"paused=" + !doesntMatterBool + "&" +
				"deleted=" + !doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns"))
			.andExpect(method(POST))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("ad-campaigns-single"), APPLICATION_JSON));
		
		Campaign campaign = twitter.advertisingOperations().createCampaign(
				mockedAccountId,
				new CampaignDataBuilder()
					.withName(doesntMatterString)
					.withCurrency(doesntMatterString)
					.withFundingInstrument(doesntMatterString)
					.withBudget(doesntMatterDecimal, doesntMatterDecimal.add(new BigDecimal(15)))
					.activeBetween(doesntMatterDate, doesntMatterDate.plusDays(1))
					.withStandardDelivery(doesntMatterBool)
					.paused()
					.deleted());
		
		assertSingleCampaignContents(campaign);
	}
	
	@Test
	public void updateCampaign() throws UnsupportedEncodingException {
		String doesntMatterString = "doesn-matter-altered";
		BigDecimal doesntMatterDecimal = new BigDecimal(2.00);
		LocalDateTime doesntMatterDate = LocalDateTime.now();
		Boolean doesntMatterBool = false; 
		String mockedCampaignId = "92ph";
		String mockedAccountId = "1ga1yn";
		
		String chainedPostContent = 
				"name=" + doesntMatterString + "&" +
				"currency=" + doesntMatterString + "&" +
				"funding_instrument_id=" + doesntMatterString + "&" +
				"total_budget_amount_local_micro=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"daily_budget_amount_local_micro=" + doesntMatterDecimal.add(new BigDecimal(3)).multiply(new BigDecimal(1000000L)) + "&" +
				"start_time=" + URLEncoder.encode(doesntMatterDate.toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"end_time=" + URLEncoder.encode(doesntMatterDate.plusDays(3).toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"standard_delivery=" + !doesntMatterBool + "&" +
				"paused=" + doesntMatterBool + "&" +
				"deleted=" + doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns/" + mockedCampaignId))
			.andExpect(method(PUT))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess());
		
		twitter.advertisingOperations().updateCampaign(
				mockedAccountId,
				mockedCampaignId,
				new CampaignDataBuilder()
					.withName(doesntMatterString)
					.withCurrency(doesntMatterString)
					.withFundingInstrument(doesntMatterString)
					.withBudget(doesntMatterDecimal, doesntMatterDecimal.add(new BigDecimal(3)))
					.activeBetween(doesntMatterDate, doesntMatterDate.plusDays(3))
					.withStandardDelivery(!doesntMatterBool)
					.unpaused()
					.active());
	}
	
	@Test
	public void deleteCampaign() {
		String mockedAccountId = "0ga0yn";
		String mockedCampaignId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns/" + mockedCampaignId))
			.andExpect(method(DELETE))
			.andRespond(withSuccess());
	
		twitter.advertisingOperations().deleteCampaign(mockedAccountId, mockedCampaignId);
	}
	
	@Test
	public void getFundingInstruments() {
		String mockedAccountId = "hkk5";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/funding_instruments?with_deleted=true"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-funding-instruments"), APPLICATION_JSON));
	
		List<FundingInstrument> fundingInstruments = twitter.advertisingOperations().getFundingInstruments(mockedAccountId);
		assertFundingInstrumentContents(fundingInstruments);
	}
	
	@Test
	public void getLineItems() {
		String mockedAccountId = "hkk5";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/line_items?with_deleted=true"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("line-items"), APPLICATION_JSON));
	
		List<LineItem> lineItems = twitter.advertisingOperations().getLineItems(mockedAccountId);
		assertLineItemContents(lineItems);
	}
	
	@Test
	public void getLineItem() {
		String mockedAccountId = "hkk5";
		String mockedLineItem = "5woz";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/line_items/" + mockedLineItem))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("line-items-single"), APPLICATION_JSON));
	
		LineItem lineItem = twitter.advertisingOperations().getLineItem(mockedAccountId, mockedLineItem);
		assertSingleLineItemContents(lineItem);
	}
	
	@Test
	public void createLineItem() {
		String mockedAccountId = "hkk5";
		String doesntMatterString = "doesn-matter-altered";
		BigDecimal doesntMatterDecimal = new BigDecimal(1.00);
		Boolean doesntMatterBool = false;
		
		String chainedPostContent = 
				"campaign_id=" + doesntMatterString + "&" +
				"currency=" + doesntMatterString + "&" +
				"placement_type=" + AdvertisingPlacementType.PROMOTED_TWEETS_FOR_SEARCH + "&" +
				"objective=" +  AdvertisingObjective.APP_INSTALLS + "&" +
				"include_sentiment=" + AdvertisingSentiment.POSITIVE_ONLY + "&" +
				"optimization=" + LineItemOptimization.WEBSITE_CONVERSIONS + "&" +
				"total_budget_amount=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"bid_amount=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"suggested_high_cpe_bid=" + doesntMatterDecimal.add(new BigDecimal(10)).multiply(new BigDecimal(1000000L)) + "&" +
				"suggested_low_cpe_bid=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"paused=" + !doesntMatterBool + "&" +
				"deleted=" + doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/line_items"))
			.andExpect(method(POST))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("line-items-single"), APPLICATION_JSON));
	
		LineItem lineItem = twitter.advertisingOperations().createLineItem(
				mockedAccountId,
				new LineItemDataBuilder()
					.withCampaign(doesntMatterString)
					.withCurrency(doesntMatterString)
					.withTotalBudget(doesntMatterDecimal)
					.withBidAmount(doesntMatterDecimal)
					.withSuggestedCpeBid(doesntMatterDecimal, doesntMatterDecimal.add(new BigDecimal(10)))
					.withPlacementType(AdvertisingPlacementType.PROMOTED_TWEETS_FOR_SEARCH)
					.withObjective(AdvertisingObjective.APP_INSTALLS)
					.optimizingFor(LineItemOptimization.WEBSITE_CONVERSIONS)
					.includingSentiment(AdvertisingSentiment.POSITIVE_ONLY)
					.paused()
					.active());
		
		assertSingleLineItemContents(lineItem);
	}
	
	@Test
	public void updateLineItem() {
		String mockedAccountId = "hkk5";
		String mockedLineItemId = "l13r";
		String doesntMatterString = "doesn-matter-altered";
		BigDecimal doesntMatterDecimal = new BigDecimal(2.00);
		Boolean doesntMatterBool = true;
		
		String chainedPostContent = 
				"campaign_id=" + doesntMatterString + "&" +
				"currency=" + doesntMatterString + "&" +
				"placement_type=" + AdvertisingPlacementType.PROMOTED_TWEETS_FOR_TIMELINES + "&" +
				"objective=" +  AdvertisingObjective.FOLLOWERS + "&" +
				"include_sentiment=" + AdvertisingSentiment.ALL + "&" +
				"optimization=" + LineItemOptimization.DEFAULT + "&" +
				"total_budget_amount=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"bid_amount=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"suggested_high_cpe_bid=" + doesntMatterDecimal.add(new BigDecimal(10)).multiply(new BigDecimal(1000000L)) + "&" +
				"suggested_low_cpe_bid=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"paused=" + !doesntMatterBool + "&" +
				"deleted=" + doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/line_items/" + mockedLineItemId))
			.andExpect(method(PUT))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("line-items-single"), APPLICATION_JSON));
	
		twitter.advertisingOperations().updateLineItem(
				mockedAccountId,
				mockedLineItemId,
				new LineItemDataBuilder()
					.withCampaign(doesntMatterString)
					.withCurrency(doesntMatterString)
					.withTotalBudget(doesntMatterDecimal)
					.withBidAmount(doesntMatterDecimal)
					.withSuggestedCpeBid(doesntMatterDecimal, doesntMatterDecimal.add(new BigDecimal(10)))
					.withPlacementType(AdvertisingPlacementType.PROMOTED_TWEETS_FOR_TIMELINES)
					.withObjective(AdvertisingObjective.FOLLOWERS)
					.optimizingFor(LineItemOptimization.DEFAULT)
					.includingSentiment(AdvertisingSentiment.ALL)
					.unpaused()
					.deleted());
	}
	
	@Test
	public void deleteLineItem() {
		String mockedAccountId = "0ga0yn";
		String mockedLineItemId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/line_items/" + mockedLineItemId))
			.andExpect(method(DELETE))
			.andRespond(withSuccess());
	
		twitter.advertisingOperations().deleteLineItem(mockedAccountId, mockedLineItemId);
	}
	
	@Test
	public void getTargetingCriterias() {
		String mockedAccountId = "hkk5";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria?with_deleted=true"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-targeting-criteria"), APPLICATION_JSON));
	
		List<TargetingCriteria> targetingCriterias = twitter.advertisingOperations().getTargetingCriterias(mockedAccountId);
		assertTargetCriteriaContents(targetingCriterias);
	}
	
	@Test
	public void getTargetingCriteria() {
		String mockedAccountId = "hkk5";
		String mockedTargetingCriteriaId = "2rqqn";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria/" + mockedTargetingCriteriaId))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-targetingcriteria-single"), APPLICATION_JSON));
	
		TargetingCriteria criteria = twitter.advertisingOperations().getTargetingCriteria(mockedAccountId, mockedTargetingCriteriaId);
		assertSingleTargetingCriteriaContents(criteria);
	}
	
	@Test
	public void createTargetingCriteria() {
		String mockedAccountId = "hkk5";
		String doesntMatterString = "doesnt-matter";
		Boolean doesntMatterBool = false;
		String chainedPostContent = 
				"line_item_id=" + doesntMatterString + "&" +
				"name=" + doesntMatterString + "&" +
				"targeting_type=" + TargetingType.APP_STORE_CATEGORY + "&" +
				"targeting_value=" +  doesntMatterString + "&" +
				"deleted=" + doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria"))
			.andExpect(method(POST))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("ad-targetingcriteria-single"), APPLICATION_JSON));
	
		TargetingCriteria criteria = twitter.advertisingOperations().createTargetingCriteria(
				mockedAccountId,
				new TargetingCriteriaDataBuilder()
					.withLineItem(doesntMatterString)
					.withName(doesntMatterString)
					.targeting(TargetingType.APP_STORE_CATEGORY, doesntMatterString)
					.active());
		
		assertSingleTargetingCriteriaContents(criteria);
	}
	
	@Test
	public void updateTargetingCriteria() {
		String mockedAccountId = "hkk5";
		String mockedTargetingCriteriaId = "2rqqn";
		String doesntMatterString = "doesnt-matter";
		Boolean doesntMatterBool = false;
		String chainedPostContent = 
				"line_item_id=" + doesntMatterString + "&" +
				"name=" + doesntMatterString + "&" +
				"targeting_type=" + TargetingType.APP_STORE_CATEGORY + "&" +
				"targeting_value=" +  doesntMatterString + "&" +
				"deleted=" + doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria/" + mockedTargetingCriteriaId))
			.andExpect(method(PUT))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("ad-targetingcriteria-single"), APPLICATION_JSON));
	
		twitter.advertisingOperations().updateTargetingCriteria(
				mockedAccountId,
				mockedTargetingCriteriaId,
				new TargetingCriteriaDataBuilder()
					.withLineItem(doesntMatterString)
					.withName(doesntMatterString)
					.targeting(TargetingType.APP_STORE_CATEGORY, doesntMatterString)
					.active());
	}
	
	@Test
	public void deleteTargetingCriteria() {
		String mockedAccountId = "0ga0yn";
		String mockedLineItemId = "92ph";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria/" + mockedLineItemId))
			.andExpect(method(DELETE))
			.andRespond(withSuccess());
	
		twitter.advertisingOperations().deleteTargetingCriteria(mockedAccountId, mockedLineItemId);
	}
	
	private void assertAdAccountContents(List<AdvertisingAccount> accounts) {
		assertEquals(2, accounts.size());
		
		assertEquals("l0l0l0", accounts.get(0).getId());
		assertEquals("h1234jasd", accounts.get(0).getName());
		assertEquals("699169a7693e571000000fef0ef0ef09", accounts.get(0).getSalt());
		assertEquals(ApprovalStatus.ACCEPTED, accounts.get(0).getApprovalStatus());
		assertEquals(TimeZone.getTimeZone("America/Los_Angeles"), accounts.get(0).getTimeZone());
		assertEquals(LocalDateTime.of(2013,Month.MAY,22,07,00,00), accounts.get(0).getTimeZoneSwitchAt());
		assertEquals(LocalDateTime.of(2013,Month.MARCH,05,21,57,11), accounts.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2015,Month.FEBRUARY,21,03,15,30), accounts.get(0).getUpdatedAt());
		assertEquals(false, accounts.get(0).isDeleted());
		
		assertEquals("l1l1l1", accounts.get(1).getId());
		assertEquals("test02483", accounts.get(1).getName());
		assertEquals("abababababababababababababababab", accounts.get(1).getSalt());
		assertEquals(ApprovalStatus.ACCEPTED, accounts.get(1).getApprovalStatus());
		assertEquals(TimeZone.getTimeZone("America/Los_Angeles"), accounts.get(1).getTimeZone());
		assertEquals(LocalDateTime.of(2013,Month.JANUARY,01,01,01,01), accounts.get(1).getTimeZoneSwitchAt());
		assertEquals(LocalDateTime.of(2011,Month.JANUARY,01,01,01,01), accounts.get(1).getCreatedAt());
		assertEquals(LocalDateTime.of(2012,Month.JANUARY,01,01,01,01), accounts.get(1).getUpdatedAt());
		assertEquals(false, accounts.get(0).isDeleted());
	}
	
	private void assertCampaignContents(List<Campaign> campaigns) {
		assertEquals(1, campaigns.size());
		
		assertEquals("1850jm", campaigns.get(0).getId());
		assertEquals("C1-oldlalala-generic", campaigns.get(0).getName());
		assertEquals("0ga0yn", campaigns.get(0).getAccountId());
		assertEquals("USD", campaigns.get(0).getCurrency());
		
		assertEquals(new BigDecimal(45.00), campaigns.get(0).getTotalBudget());
		assertEquals(new BigDecimal(10.00), campaigns.get(0).getDailyBudget());
		
		assertEquals(LocalDateTime.of(2014, Month.MAY, 15, 00, 12, 00), campaigns.get(0).getStartTime());
		assertEquals(LocalDateTime.of(2014, Month.MAY, 16, 22, 00, 00), campaigns.get(0).getEndTime());
		assertEquals(LocalDateTime.of(2014, Month.MAY, 15, 01, 17, 47), campaigns.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2014, Month.MAY, 16, 20, 41, 38), campaigns.get(0).getUpdatedAt());
		
		assertThat(campaigns.get(0).getReasonsNotServable(), not(hasItem(ReasonNotServable.DELETED)));
		assertThat(campaigns.get(0).getReasonsNotServable(), hasItem(ReasonNotServable.EXPIRED));
		assertThat(campaigns.get(0).getReasonsNotServable(), hasItem(ReasonNotServable.PAUSED_BY_ADVERTISER));
		
		assertEquals(true, campaigns.get(0).isStandardDelivery());
		assertEquals(false, campaigns.get(0).isServable());
		assertEquals(true, campaigns.get(0).isPaused());
		assertEquals(false, campaigns.get(0).isDeleted());
	}
	
	private void assertSingleCampaignContents(Campaign campaign) {
		assertEquals("1ga1yn", campaign.getAccountId());
		assertEquals("92ph", campaign.getId());
		assertEquals("My First Campaign", campaign.getName());
		assertEquals("USD", campaign.getCurrency());
		assertEquals("yyyy", campaign.getFundingInstrumentId());
		
		assertEquals(new BigDecimal(500.00), campaign.getTotalBudget());
		assertEquals(new BigDecimal(40.00), campaign.getDailyBudget());
		
		assertEquals(LocalDateTime.of(2015, Month.FEBRUARY, 9, 00, 00, 00), campaign.getStartTime());
		assertEquals(null, campaign.getEndTime());
		
		assertThat(campaign.getReasonsNotServable(), empty());
		assertEquals(true, campaign.isStandardDelivery());
		assertEquals(false, campaign.isPaused());
		assertEquals(true, campaign.isServable());
		assertEquals(false, campaign.isDeleted());
	}	
	
	private void assertFundingInstrumentContents(List<FundingInstrument> fundingInstruments) {
		assertEquals(2, fundingInstruments.size());
		
		assertEquals("hw6ie", fundingInstruments.get(0).getId());
		assertEquals(FundingInstrumentType.CREDIT_CARD, fundingInstruments.get(0).getType());
		assertEquals("hkk5", fundingInstruments.get(0).getAccountId());
		assertEquals("MasterCard ending in 1234", fundingInstruments.get(0).getDescription());
		assertEquals("USD", fundingInstruments.get(0).getCurrency());
		assertEquals(new BigDecimal(1000.00), fundingInstruments.get(0).getCreditLimit());
		assertEquals(null, fundingInstruments.get(0).getCreditRemaining());
		assertEquals(new BigDecimal(100.00), fundingInstruments.get(0).getFundedAmount());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 8, 02, 31, 46), fundingInstruments.get(0).getStartTime());
		assertEquals(null, fundingInstruments.get(0).getEndTime());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 8, 02, 31, 46), fundingInstruments.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 20, 23, 20, 35), fundingInstruments.get(0).getUpdatedAt());
		assertEquals(false, fundingInstruments.get(0).isCancelled());
		assertEquals(true, fundingInstruments.get(0).isDeleted());
		
		assertEquals("i1234", fundingInstruments.get(1).getId());
		assertEquals(FundingInstrumentType.CREDIT_LINE, fundingInstruments.get(1).getType());
		assertEquals("hkk5", fundingInstruments.get(1).getAccountId());
		assertEquals("FakeNike - Credit Line", fundingInstruments.get(1).getDescription());
		assertEquals("GBP", fundingInstruments.get(1).getCurrency());
		assertEquals(new BigDecimal(150000.00).round(MathContext.DECIMAL32), fundingInstruments.get(1).getCreditLimit());
		assertEquals(new BigDecimal(123661.919751).round(MathContext.DECIMAL32), fundingInstruments.get(1).getCreditRemaining());
		assertEquals(new BigDecimal(0.00).round(MathContext.DECIMAL32), fundingInstruments.get(1).getFundedAmount());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 04, 00, 00), fundingInstruments.get(1).getStartTime());
		assertEquals(null, fundingInstruments.get(1).getEndTime());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 18, 16, 38), fundingInstruments.get(1).getCreatedAt());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 18, 16, 38), fundingInstruments.get(1).getUpdatedAt());
		assertEquals(false, fundingInstruments.get(1).isCancelled());
		assertEquals(false, fundingInstruments.get(1).isDeleted());
	}
	
	private void assertLineItemContents(List<LineItem> lineItems) {
		assertEquals(1, lineItems.size());
		
		assertEquals("5woz", lineItems.get(0).getId());
		assertEquals("hkk5", lineItems.get(0).getAccountId());
		assertEquals("7jem", lineItems.get(0).getCampaignId());
		assertEquals("USD", lineItems.get(0).getCurrency());
		assertEquals(AdvertisingPlacementType.PROMOTED_TWEETS_FOR_TIMELINES, lineItems.get(0).getPlacementType());
		assertEquals(AdvertisingObjective.TWEET_ENGAGEMENTS, lineItems.get(0).getObjective());
		assertEquals(AdvertisingSentiment.POSITIVE_ONLY, lineItems.get(0).getIncludeSentiment());
		assertEquals(LineItemOptimization.DEFAULT, lineItems.get(0).getOptimization());
		assertEquals(null, lineItems.get(0).getTotalBudgetAmount());
		assertEquals(new BigDecimal(0.69).round(MathContext.DECIMAL32), lineItems.get(0).getBidAmount());
		assertEquals(null, lineItems.get(0).getSuggestedHighCpeBid());
		assertEquals(null, lineItems.get(0).getSuggestedLowCpeBid());
		assertEquals(false, lineItems.get(0).isPaused());
		assertEquals(true, lineItems.get(0).isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 20, 23, 29, 10), lineItems.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.DECEMBER, 06, 00, 53, 57), lineItems.get(0).getUpdatedAt());
	}
	
	private void assertSingleLineItemContents(LineItem lineItem) {
		assertEquals("69ob", lineItem.getId());
		assertEquals("hkk5", lineItem.getAccountId());
		assertEquals("7wdy", lineItem.getCampaignId());
		assertEquals("GBP", lineItem.getCurrency());
		assertEquals(AdvertisingPlacementType.PROMOTED_ACCOUNT, lineItem.getPlacementType());
		assertEquals(AdvertisingObjective.FOLLOWERS, lineItem.getObjective());
		assertEquals(null, lineItem.getIncludeSentiment());
		assertEquals(LineItemOptimization.DEFAULT, lineItem.getOptimization());
		assertEquals(null, lineItem.getTotalBudgetAmount());
		assertEquals(new BigDecimal(1.75).round(MathContext.DECIMAL32), lineItem.getBidAmount());
		assertEquals(null, lineItem.getSuggestedHighCpeBid());
		assertEquals(null, lineItem.getSuggestedLowCpeBid());
		assertEquals(false, lineItem.isPaused());
		assertEquals(false, lineItem.isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 11), lineItem.getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 11), lineItem.getUpdatedAt());
	}
	
	private void assertTargetCriteriaContents(List<TargetingCriteria> criterias) {
		assertEquals(4, criterias.size());
		
		assertEquals("2kzxf", criterias.get(0).getId());
		assertEquals("hkk5", criterias.get(0).getAccountId());
		assertEquals("69ob", criterias.get(0).getLineItemId());
		assertEquals("episod", criterias.get(0).getName());
		assertEquals(TargetingType.SIMILAR_TO_FOLLOWERS_OF_USER, criterias.get(0).getTargetingType());
		assertEquals("819797", criterias.get(0).getTargetingValue());
		assertEquals(false, criterias.get(0).isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 11), criterias.get(0).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 11), criterias.get(0).getUpdatedAt());
		
		assertEquals("2kzxi", criterias.get(1).getId());
		assertEquals("hkk5", criterias.get(1).getAccountId());
		assertEquals("69ob", criterias.get(1).getLineItemId());
		assertEquals("matrixsynth", criterias.get(1).getName());
		assertEquals(TargetingType.SIMILAR_TO_FOLLOWERS_OF_USER, criterias.get(1).getTargetingType());
		assertEquals("22231561", criterias.get(1).getTargetingValue());
		assertEquals(false, criterias.get(1).isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 12), criterias.get(1).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 12), criterias.get(1).getUpdatedAt());
		
		assertEquals("2kzxj", criterias.get(2).getId());
		assertEquals("hkk5", criterias.get(2).getAccountId());
		assertEquals("69ob", criterias.get(2).getLineItemId());
		assertEquals("Horse_ebooks", criterias.get(2).getName());
		assertEquals(TargetingType.SIMILAR_TO_FOLLOWERS_OF_USER, criterias.get(2).getTargetingType());
		assertEquals("174958347", criterias.get(2).getTargetingValue());
		assertEquals(false, criterias.get(2).isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 12), criterias.get(2).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.NOVEMBER, 30, 22, 58, 12), criterias.get(2).getUpdatedAt());
		
		assertEquals("2mq7j", criterias.get(3).getId());
		assertEquals("hkk5", criterias.get(3).getAccountId());
		assertEquals("69ob", criterias.get(3).getLineItemId());
		assertEquals("righteous dude", criterias.get(3).getName());
		assertEquals(TargetingType.PHRASE_KEYWORD, criterias.get(3).getTargetingType());
		assertEquals("righteous dude", criterias.get(3).getTargetingValue());
		assertEquals(true, criterias.get(3).isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.DECEMBER, 05, 05, 11, 15), criterias.get(3).getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.DECEMBER, 06, 05, 11, 15), criterias.get(3).getUpdatedAt());
	}
	
	private void assertSingleTargetingCriteriaContents(TargetingCriteria criteria) {
		assertEquals("2rqqn", criteria.getId());
		assertEquals("hkk5", criteria.getAccountId());
		assertEquals("6zva", criteria.getLineItemId());
		assertEquals("Portland OR, US", criteria.getName());
		assertEquals(TargetingType.LOCATION, criteria.getTargetingType());
		assertEquals("b6b8d75a320f81d9", criteria.getTargetingValue());
		assertEquals(false, criteria.isDeleted());
		assertEquals(LocalDateTime.of(2012, Month.DECEMBER, 13, 22, 51, 32), criteria.getCreatedAt());
		assertEquals(LocalDateTime.of(2012, Month.DECEMBER, 13, 22, 51, 32), criteria.getUpdatedAt());
	}
}
