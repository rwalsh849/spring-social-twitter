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
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.domain.models.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.domain.models.advertising.FundingInstrument;
import org.springframework.social.twitter.api.domain.operations.advertising.AdvertisingOperations;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriBuilder;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriResourceForAdvertising;
import org.springframework.social.twitter.api.impl.common.holders.DataListHolder;
import org.springframework.social.twitter.api.impl.common.templates.AbstractTwitterTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class AdvertisingTemplate extends AbstractTwitterTemplate implements AdvertisingOperations {
	private final RestTemplate restTemplate;

	public AdvertisingTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<AdvertisingAccount> getAccounts() {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.ACCOUNTS)
					.withArgument("with_deleted", "true")
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<AdvertisingAccount>>(){}
			).getBody().getData();
	}
	
	@Override
	public List<FundingInstrument> getFundingInstruments(String accountId) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.FUNDING_INSTRUMENTS)
					.withArgument("account_id", accountId)
					.withArgument("with_deleted", "true")
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<FundingInstrument>>(){}
			).getBody().getData();
	}
	
}
