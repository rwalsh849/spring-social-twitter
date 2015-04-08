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

import java.net.URI;
import java.util.List;

import org.springframework.social.twitter.api.AdvertisingAccount;
import org.springframework.social.twitter.api.AdvertisingOperations;
import org.springframework.social.twitter.api.Campaign;
import org.springframework.social.twitter.api.FundingInstrument;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class AdvertisingTemplate extends AbstractTwitterOperations implements AdvertisingOperations {
	private final RestTemplate restTemplate;

	public AdvertisingTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<AdvertisingAccount> getAccounts() {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.ACCOUNT;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).build();
		AdvertisingAccountList data = restTemplate.getForObject(resourceUri, AdvertisingAccountList.class);
		return data.getList();
	}
	
	@Override
	public Campaign getCampaign(String accountId, String id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<Campaign> getCampaigns(String accountId) {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.CAMPAIGN;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("account_id", accountId).build();
		CampaignList data = restTemplate.getForObject(resourceUri, CampaignList.class);
		return data.getList();
	}
	
	@Override
	public List<FundingInstrument> getFundingInstruments(String accountId) {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.FUNDING_INSTRUMENTS;
		LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("account_id", accountId);
		parameters.set("with_deleted", "true");
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument(parameters).build();
		FundingInstrumentList data = restTemplate.getForObject(resourceUri, FundingInstrumentList.class);
		return data.getList();
	}

	@Override
	public Campaign createCampaign(String accountId, CampaignData data) {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.CAMPAIGN;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("account_id", accountId).build();
		MultiValueMap<String, Object> params = data.toRequestParameters();
		CampaignResult result = restTemplate.postForObject(resourceUri, params, CampaignResult.class);
		return result.getCampaign();
	}

	@Override
	public Campaign updateCampaign(String accountId, String id, CampaignData data) {
		requireUserAuthorization();
		TwitterApiUriResourceForAdvertising resource = TwitterApiUriResourceForAdvertising.CAMPAIGN;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("account_id", accountId).build();
		MultiValueMap<String, Object> params = data.toRequestParameters();
		restTemplate.put(resourceUri, params);
		return this.getCampaign(accountId, id);
	}
}
