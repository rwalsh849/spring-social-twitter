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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.StatsGranularity;
import org.springframework.social.twitter.api.advertising.StatsMetric;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Basic representation of the QueryString parameters builders
 * that shall be used for querying any data in the APIs.
 * Important: unfortunately, the basic API is a lot less standardized than the ADs Api
 *            and therefore we cannot use this base builder for _everything_. However,
 *            it's reasonable the Twitter moves towards standardization and then, this
 *            builder will become a richer asset to the Api.
 * @author Hudson Mendes
 */
public abstract class AbstractTwitterQueryForStatsBuilder<TBuilderInterface>
	extends AbstractTwitterParametersBuilder {
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private StatsGranularity granularity;
	private List<StatsMetric> metrics;
	
	public MultiValueMap<String, Object> toQueryParameters() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		makeParameters(map);
		appendParameter(map, "granularity", this.granularity);
		appendParameter(map, "metrics", this.metrics);
		if (this.startTime != null) appendParameter(map, "start_time", this.startTime.toInstant(ZoneOffset.UTC));
		if (this.endTime != null) appendParameter(map, "end_time", this.endTime.toInstant(ZoneOffset.UTC));
		
		return map;
	}

	public TBuilderInterface activeUntil(LocalDateTime endTime) {
		return activeBetween(null, endTime);
	}
	
	public TBuilderInterface activeFrom(LocalDateTime startTime) {
		return activeBetween(startTime, null);
	}

	@SuppressWarnings("unchecked")
	public TBuilderInterface activeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime != null) this.startTime = startTime;
		if (endTime != null) this.endTime = endTime;
		return (TBuilderInterface) this;
	}
	
	@SuppressWarnings("unchecked")
	public TBuilderInterface withGranularity(StatsGranularity granularity) {
		this.granularity = granularity;
		return (TBuilderInterface) this;
	}
	
	@SuppressWarnings("unchecked")
	public TBuilderInterface withStatisticalMetric(StatsMetric... metrics) {
		this.metrics = new ArrayList<StatsMetric>();
		for (int i = 0; i < metrics.length; i++)
			this.metrics.add(metrics[i]);
		return (TBuilderInterface) this;
	}
	
	protected abstract void makeParameters(MultiValueMap<String, Object> map);
}
