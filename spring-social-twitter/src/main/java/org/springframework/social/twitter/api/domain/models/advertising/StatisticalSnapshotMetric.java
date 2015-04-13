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
package org.springframework.social.twitter.api.domain.models.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.impl.standard.models.TwitterObject;

/**
 * Represents a metric that has been retrieved by the Ads statistics endpoint.
 * @author Hudson Mendes
 */
public class StatisticalSnapshotMetric extends TwitterObject {
	private final String name;
	private final List<Object> entries;
	
	public StatisticalSnapshotMetric(String name, List<Object> entries) {
		this.name = name;
		this.entries = new ArrayList<>(entries);
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public <TEntry> List<TEntry> entries() {
		List<TEntry> casted = new ArrayList<>();
		this.entries.forEach(i -> casted.add((TEntry) i));
		return casted;
	}
}
