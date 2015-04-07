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

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.social.twitter.api.AdAccount;
import org.springframework.social.twitter.api.AdCampaign;
import org.springframework.social.twitter.api.AdvertisingOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class AdvertisingTemplate extends AbstractTwitterOperations implements AdvertisingOperations {
	private static final BigDecimal MICRO_MULTIPLIER = new BigDecimal(1000000);
	private final RestTemplate restTemplate;

	public AdvertisingTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<AdAccount> getAccounts() {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.ACCOUNT;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).build();
		AdAccountList data = restTemplate.getForObject(resourceUri, AdAccountList.class);
		return data.getList();
	}

	@Override
	public List<AdCampaign> getCampaigns(String accountId) {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.CAMPAIGN;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("account_id", accountId).build();
		AdCampaignList data = restTemplate.getForObject(resourceUri, AdCampaignList.class);
		return data.getList();
	}

	@Override
	public AdCampaign createCampaign(
			String name, String accountId, String currency, String fundingInstrumentId,
			BigDecimal totalBudget, BigDecimal dailyBudget,
			LocalDateTime startTime, LocalDateTime endTime,
			Boolean standardDelivery, Boolean paused) {
		
		requireUserAuthorization();
		
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.CAMPAIGN;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("account_id", accountId).build();
		
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("name", name);
		data.add("account_id", accountId);
		data.add("currency", currency);
		data.add("funding_instrument_id", fundingInstrumentId);
		data.add("total_budget_amount_local_micro", translateBigDecimalIntoMicro(totalBudget).toString());
		data.add("daily_budget_amount_local_micro", translateBigDecimalIntoMicro(dailyBudget).toString());
		data.add("start_time", startTime.toInstant(ZoneOffset.UTC).toString());
		data.add("end_time", endTime.toInstant(ZoneOffset.UTC).toString());
		data.add("standard_delivery", standardDelivery.toString());
		data.add("paused", paused.toString());
		
		AdCampaignResult result = restTemplate.postForObject(resourceUri, data, AdCampaignResult.class);
		return result.getCampaign();
	}

	private Long translateBigDecimalIntoMicro(BigDecimal value) {
		return value.multiply(MICRO_MULTIPLIER).longValue();
	}
}
