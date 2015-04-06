package org.springframework.social.twitter.api;
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

/**
 * Represents an Advertising Campaign run for a particular {@link AdAccount}. 
 * @author Hudson Mendes
 */
public class AdCampaign extends TwitterObject {
	private final String id;
	private final String name;
	private final String accountId;
	private final String currency;
	
	public AdCampaign(String id, String name, String accountId, String currency) {
		this.id = id;
		this.name = name;
		this.accountId = accountId;
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCurrency() {
		return currency;
	}
}
