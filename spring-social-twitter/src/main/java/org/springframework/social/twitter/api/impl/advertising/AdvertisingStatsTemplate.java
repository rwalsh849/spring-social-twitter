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

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.QueryingData;
import org.springframework.social.twitter.api.advertising.AdvertisingStatsOperations;
import org.springframework.social.twitter.api.advertising.StatisticalSnapshot;
import org.springframework.social.twitter.api.impl.AbstractTwitterTemplate;
import org.springframework.social.twitter.api.impl.DataListHolder;
import org.springframework.social.twitter.api.impl.TwitterApiUriBuilder;
import org.springframework.social.twitter.api.impl.TwitterApiUriResourceForAdvertising;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingStatsOperations}, providing a binding to
 * Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class AdvertisingStatsTemplate extends AbstractTwitterTemplate implements AdvertisingStatsOperations {
	private final RestTemplate restTemplate;

	public AdvertisingStatsTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<StatisticalSnapshot> byCampaigns(String accountId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_CAMPAIGNS)
					.withArgument("account_id", accountId)
					.withArgument(query.toQueryParameters())
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<StatisticalSnapshot>>(){}
			).getBody().getData();
	}
	
	@Override
	public StatisticalSnapshot byCampaign(String accountId, String campaignId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_CAMPAIGN)
					.withArgument("account_id", accountId)
					.withArgument("campaign_id", campaignId)
					.withArgument(query.toQueryParameters())
					.build(),
				StatisticalSnapshot.class
			);
	}

	@Override
	public List<StatisticalSnapshot> byFundingInstruments(String accountId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_FUNDING_INSTRUMENTS)
					.withArgument("account_id", accountId)
					.withArgument(query.toQueryParameters())
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<StatisticalSnapshot>>(){}
			).getBody().getData();
	}

	@Override
	public StatisticalSnapshot byFundingInstrument(String accountId, String fundingInstrumentId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_FUNDING_INSTRUMENT)
					.withArgument("account_id", accountId)
					.withArgument("funding_instrument_id", fundingInstrumentId)
					.withArgument(query.toQueryParameters())
					.build(),
				StatisticalSnapshot.class
			);
	}

	@Override
	public List<StatisticalSnapshot> byLineItems(String accountId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_LINE_ITEMS)
					.withArgument("account_id", accountId)
					.withArgument(query.toQueryParameters())
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<StatisticalSnapshot>>(){}
			).getBody().getData();
	}

	@Override
	public StatisticalSnapshot byLineItem(String accountId, String lineItemId, QueryingData query) {
		requireUserAuthorization();
		return restTemplate.getForObject(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.STATS_LINE_ITEM)
					.withArgument("account_id", accountId)
					.withArgument("line_item_id", lineItemId)
					.withArgument(query.toQueryParameters())
					.build(),
				StatisticalSnapshot.class
			);
	}
	
}
