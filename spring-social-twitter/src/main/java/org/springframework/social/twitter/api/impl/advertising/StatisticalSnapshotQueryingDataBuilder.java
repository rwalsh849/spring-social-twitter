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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.StatisticalGranularity;
import org.springframework.social.twitter.api.advertising.StatisticalMetric;
import org.springframework.social.twitter.api.advertising.StatisticalSnapshot;
import org.springframework.social.twitter.api.impl.TwitterRequestQueryingDataBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Builder related to {@link StatisticalSnapshot} data that generates a map (key, value)
 * that can be posted into the twitter api endpoint.
 * 
 * @author Hudson Mendes
 */
public class StatisticalSnapshotQueryingDataBuilder extends TwitterRequestQueryingDataBuilder {
	private List<String> campaignIds;
	private List<String> fundingInstrumentIds;
	private List<String> lineItemIds;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private StatisticalGranularity granularity;
	private List<StatisticalMetric> metrics; 

	@Override
	public MultiValueMap<String, Object> toQueryParameters() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		appendParameter(params, "campaign_ids", this.campaignIds);
		appendParameter(params, "funding_instrument_ids", this.fundingInstrumentIds);
		appendParameter(params, "line_item_ids", this.lineItemIds);
		appendParameter(params, "granularity", this.granularity);
		appendParameter(params, "metrics", this.metrics);
		if (this.startTime != null) appendParameter(params, "start_time", this.startTime.toInstant(ZoneOffset.UTC));
		if (this.endTime != null) appendParameter(params, "end_time", this.endTime.toInstant(ZoneOffset.UTC));
				
		return params;
	}
	
	public StatisticalSnapshotQueryingDataBuilder withCampaigns(String... campaignIds) {
		this.campaignIds = new ArrayList<String>();
		for (int i = 0; i < campaignIds.length; i++)
			this.campaignIds.add(campaignIds[i]);
		return this;
	}
	
	public StatisticalSnapshotQueryingDataBuilder withFundingInstruments(String... fundingInstrumentIds) {
		this.fundingInstrumentIds = new ArrayList<String>();
		for (int i = 0; i < fundingInstrumentIds.length; i++)
			this.fundingInstrumentIds.add(fundingInstrumentIds[i]);
		return this;
	}
	
	public StatisticalSnapshotQueryingDataBuilder withLineItems(String... lineItemIds) {
		this.lineItemIds = new ArrayList<String>();
		for (int i = 0; i < lineItemIds.length; i++)
			this.lineItemIds.add(lineItemIds[i]);
		return this;
	}
	
	public StatisticalSnapshotQueryingDataBuilder activeUntil(LocalDateTime endTime) {
		return activeBetween(null, endTime);
	}
	
	public StatisticalSnapshotQueryingDataBuilder activeFrom(LocalDateTime startTime) {
		return activeBetween(startTime, null);
	}

	public StatisticalSnapshotQueryingDataBuilder activeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime != null) this.startTime = startTime;
		if (endTime != null) this.endTime = endTime;
		return this;
	}
	
	public StatisticalSnapshotQueryingDataBuilder withGranularity(StatisticalGranularity granularity) {
		this.granularity = granularity;
		return this;
	}
	
	public StatisticalSnapshotQueryingDataBuilder withStatisticalMetric(StatisticalMetric... metrics) {
		this.metrics = new ArrayList<StatisticalMetric>();
		for (int i = 0; i < metrics.length; i++)
			this.metrics.add(metrics[i]);
		return this;
	}
	
}
