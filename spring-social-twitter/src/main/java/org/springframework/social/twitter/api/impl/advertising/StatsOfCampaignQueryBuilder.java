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

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.StatsSnapshot;
import org.springframework.social.twitter.api.advertising.StatsOfCampaignQuery;
import org.springframework.social.twitter.api.impl.AbstractTwitterQueryForStatsBuilder;
import org.springframework.util.MultiValueMap;

/**
 * Builder related to {@link StatsSnapshot} data that generates a map (key, value)
 * that can be posted into the twitter api endpoint.
 * 
 * @author Hudson Mendes
 */
public class StatsOfCampaignQueryBuilder extends AbstractTwitterQueryForStatsBuilder<StatsOfCampaignQuery> implements StatsOfCampaignQuery {
	private List<String> campaignIds;
	
	/* (non-Javadoc)
	 * @see org.springframework.social.twitter.api.impl.advertising.TwitterQueryForStatsOfCampaign#withCampaigns(java.lang.String)
	 */
	@Override
	public StatsOfCampaignQueryBuilder withCampaigns(String... campaignIds) {
		this.campaignIds = new ArrayList<String>();
		for (int i = 0; i < campaignIds.length; i++)
			this.campaignIds.add(campaignIds[i]);
		return this;
	}

	@Override
	protected void makeParameters(MultiValueMap<String, Object> map) {
		appendParameter(map, "campaign_ids", this.campaignIds);
	}
}
