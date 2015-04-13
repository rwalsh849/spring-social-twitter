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
package org.springframework.social.twitter.api.impl.advertising.builders;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.domain.models.advertising.StatisticalGranularity;
import org.springframework.social.twitter.api.domain.models.advertising.StatisticalSnapshot;
import org.springframework.social.twitter.api.impl.common.builders.TwitterRequestQueryingDataBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Builder related to {@link StatisticalSnapshot} data that generates a map (key, value)
 * that can be posted into the twitter api endpoint.
 * 
 * @author Hudson Mendes
 */
public class StatsQueryingDataBuilder extends TwitterRequestQueryingDataBuilder {
	private List<String> campaignIds;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private StatisticalGranularity granularity;
	private List<StatisticalMetric> metrics; 

	@Override
	public MultiValueMap<String, Object> toQueryParameters() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		if (this.campaignIds != null) this.campaignIds.forEach(id -> appendParameter(params, "campaign_ids", id));
		if (this.metrics != null) this.campaignIds.forEach(metric -> appendParameter(params, "metrics", metric));
		appendParameter(params, "start_time", this.startTime.toInstant(ZoneOffset.UTC));
		appendParameter(params, "end_time", this.endTime.toInstant(ZoneOffset.UTC));
		appendParameter(params, "granularity", this.granularity.toString());
				
		return params;
	}
	
	public StatsQueryingDataBuilder withCampaigns(List<String> campaignIds) {
		this.campaignIds = new ArrayList<String>(campaignIds);
		return this;
	}
	
	public StatsQueryingDataBuilder activeUntil(LocalDateTime endTime) {
		return activeBetween(null, endTime);
	}
	
	public StatsQueryingDataBuilder activeFrom(LocalDateTime startTime) {
		return activeBetween(startTime, null);
	}

	public StatsQueryingDataBuilder activeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		return this;
	}
	
	public StatsQueryingDataBuilder withGranularity(StatisticalGranularity granularity) {
		this.granularity = granularity;
		return this;
	}
	
	public StatsQueryingDataBuilder withStatisticalMetric(List<StatisticalMetric> metrics) {
		this.metrics = new ArrayList<StatisticalMetric>(metrics);
		return this;
	}
	
}
