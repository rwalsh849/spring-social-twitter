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

import java.util.List;

import org.springframework.social.twitter.api.AdvertisingAccount;
import org.springframework.social.twitter.api.Campaign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Typed list of {@link Campaign} related to a {@link AdvertisingAccount}
 * This helps Jackson know what type to deserialize list content into.
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CampaignList {
	private final List<Campaign> list;
	
	@JsonCreator
	public CampaignList(@JsonProperty("data") List<Campaign> list) {
		this.list = list;
	}
	
	public List<Campaign> getList() {
		return this.list;
	}
}
