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
package org.springframework.social.twitter.api.impl.advertising.templates;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.domain.models.TransferingData;
import org.springframework.social.twitter.api.domain.models.advertising.Campaign;
import org.springframework.social.twitter.api.domain.operations.advertising.AdvertisingOperations;
import org.springframework.social.twitter.api.domain.operations.advertising.CampaignOperations;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriBuilder;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriResourceForAdvertising;
import org.springframework.social.twitter.api.impl.common.holders.DataListHolder;
import org.springframework.social.twitter.api.impl.common.holders.DataSingleHolder;
import org.springframework.social.twitter.api.impl.common.templates.AbstractTwitterTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class CampaignTemplate extends AbstractTwitterTemplate implements CampaignOperations {
	private final RestTemplate restTemplate;

	public CampaignTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Campaign getCampaign(String accountId, String id) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.CAMPAIGN)
					.withArgument("account_id", accountId)
					.withArgument("campaign_id", id)
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataSingleHolder<Campaign>>(){}
			).getBody().getData();
	}

	@Override
	public List<Campaign> getCampaigns(String accountId) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.CAMPAIGNS)
					.withArgument("account_id", accountId)
					.withArgument("with_deleted", "true")
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<Campaign>>(){}
			).getBody().getData();
	}
	
	@Override
	public Campaign createCampaign(String accountId, TransferingData data) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.CAMPAIGNS)
					.withArgument("account_id", accountId)
					.build(),
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, Object>>(data.toRequestParameters()),
				new ParameterizedTypeReference<DataSingleHolder<Campaign>>(){}
			).getBody().getData();
	}

	@Override
	public void updateCampaign(String accountId, String id, TransferingData data) {
		requireUserAuthorization();
		restTemplate.put(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.CAMPAIGN)
					.withArgument("account_id", accountId)
					.withArgument("campaign_id", id)
					.build(),
				data.toRequestParameters());
	}
	
	@Override
	public void deleteCampaign(String accountId, String id) {
		requireUserAuthorization();
		restTemplate.delete(new TwitterApiUriBuilder()
			.withResource(TwitterApiUriResourceForAdvertising.CAMPAIGN)
			.withArgument("account_id", accountId)
			.withArgument("campaign_id", id)
			.build());
	}
	
}
