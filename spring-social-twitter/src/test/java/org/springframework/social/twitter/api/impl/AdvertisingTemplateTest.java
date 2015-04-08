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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.social.twitter.api.AdvertisingAccount;
import org.springframework.social.twitter.api.Campaign;
import org.springframework.social.twitter.api.ApprovalStatus;
import org.springframework.social.twitter.api.FundingInstrument;
import org.springframework.social.twitter.api.FundingInstrumentType;
import org.springframework.social.twitter.api.ReasonNotServable;

/**
 * @author Hudson mendes
 */
public class AdvertisingTemplateTest extends AbstractTwitterApiTest {

	@Test
	public void getAccounts() {
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-accounts"), APPLICATION_JSON));

		List<AdvertisingAccount> accounts = twitter.advertisingOperations().getAccounts();
		assertAdAccountContents(accounts);
	}
	
	@Test
	public void getCampaigns() {
		String mockedAccountId = "0ga0yn";
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns"))
			.andExpect(method(GET))
			.andRespond(withSuccess(jsonResource("ad-campaigns"), APPLICATION_JSON));
	
		List<Campaign> campaigns = twitter.advertisingOperations().getCampaigns(mockedAccountId);
		assertCampaignContents(campaigns);
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
	public void createCampaign() throws UnsupportedEncodingException {
		String doesntMatterString = "doesn-matter";
		BigDecimal doesntMatterDecimal = new BigDecimal(1.00);
		LocalDateTime doesntMatterDate = LocalDateTime.now();
		Boolean doesntMatterBool = false; 
		String mockedAccountId = "1ga1yn";
		
		String chainedPostContent = 
				"account_id=" + mockedAccountId + "&" +
				"name=" + doesntMatterString + "&" +
				"currency=" + doesntMatterString + "&" +
				"funding_instrument_id=" + doesntMatterString + "&" +
				"total_budget_amount_local_micro=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"daily_budget_amount_local_micro=" + doesntMatterDecimal.multiply(new BigDecimal(1000000L)) + "&" +
				"start_time=" + URLEncoder.encode(doesntMatterDate.toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"end_time=" + URLEncoder.encode(doesntMatterDate.plusDays(1).toInstant(ZoneOffset.UTC).toString(),"UTF-8") + "&" +
				"standard_delivery=" + doesntMatterBool + "&" +
				"paused=" + !doesntMatterBool;
		
		mockServer
			.expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/campaigns"))
			.andExpect(method(POST))
			.andExpect(content().string(chainedPostContent))
			.andRespond(withSuccess(jsonResource("ad-campaigns-create"), APPLICATION_JSON));
		
		Campaign campaign = twitter.advertisingOperations().createCampaign(
				mockedAccountId,
				new CampaignData()
					.withName(doesntMatterString)
					.withCurrency(doesntMatterString)
					.withFundingInstrument(doesntMatterString)
					.withBudget(doesntMatterDecimal.multiply(new BigDecimal(1000000L)), doesntMatterDecimal.multiply(new BigDecimal(1000000L)))
					.activeBetween(doesntMatterDate, doesntMatterDate.plusDays(1))
					.withStandardDelivery(doesntMatterBool)
					.paused());
		
		asserSingleAdCampaignContents(campaign);
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
		assertEquals(new BigDecimal(150000.00), fundingInstruments.get(1).getCreditLimit());
		assertEquals(new BigDecimal(123661.919751), fundingInstruments.get(1).getCreditRemaining());
		assertEquals(new BigDecimal(0.00), fundingInstruments.get(1).getFundedAmount());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 04, 00, 00), fundingInstruments.get(1).getStartTime());
		assertEquals(null, fundingInstruments.get(1).getEndTime());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 18, 16, 38), fundingInstruments.get(1).getCreatedAt());
		assertEquals(LocalDateTime.of(2013, Month.MAY, 30, 18, 16, 38), fundingInstruments.get(1).getUpdatedAt());
		assertEquals(false, fundingInstruments.get(1).isCancelled());
		assertEquals(false, fundingInstruments.get(1).isDeleted());
	}
	
	private void asserSingleAdCampaignContents(Campaign campaign) {
		assertEquals("92ph", campaign.getId());
		assertEquals("My First Campaign", campaign.getName());
		assertEquals("1ga1yn", campaign.getAccountId());
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
	
}
