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

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

import org.springframework.social.twitter.api.advertising.StatsGranularity;
import org.springframework.social.twitter.api.advertising.StatsMetric;
import org.springframework.social.twitter.api.advertising.StatsSnapshot;
import org.springframework.social.twitter.api.advertising.StatsSnapshotMetric;
import org.springframework.social.twitter.api.impl.LocalDateTimeDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Deserializes the complex object {@link StatsSnapshot}
 * Differently from the other REST objects in the domain, the {@link StatsSnapshot}
 * has a very fluent interface and changes according to the parameters passed to the endpoint.
 * 
 * This deserializer tackles this complexity by getting the flexible JSON parts and pushing them
 * into a rigit model {@link StatsSnapshotMetric} 
 * 
 * @author hudson
 *
 */
public class StatsSnapshotDeserializer extends JsonDeserializer<StatsSnapshot> {
	private static final Map<StatsMetric, Type> mapOfMetrics = new StatsSnapshotMetricMapBuilder().build();

	@Override
	public StatsSnapshot deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec codec = p.getCodec();
		JsonNode root = codec.readTree(p);
		JsonNode data = root.get("data");
		if (data == null) data = root;
		
		return new StatsSnapshot(
				extractId(data),
				extractGranularity(data),
				extractMetrics(data),
				extractStartTime(data),
				extractEndTime(data));
	}
	
	private String extractId(JsonNode data) {
		return data.get("id").asText();
	}
	
	private StatsGranularity extractGranularity(JsonNode data) {
		return StatsGranularity.valueOf(data.get("granularity").asText());
	}
	
	private Map<StatsMetric, StatsSnapshotMetric> extractMetrics(JsonNode data) {
		Map<StatsMetric, StatsSnapshotMetric> map = new HashMap<StatsMetric, StatsSnapshotMetric>();
		StatsMetric[] values = StatsMetric.class.getEnumConstants();
		for (int i = 0; i < values.length; i++) {
			StatsMetric metric = values[i];
			JsonNode metricNode = data.get(metric.toString());
			if (metricNode != null) {
				List<Object> entries = new ArrayList<Object>();
				parseMetricEntries(metric, metricNode.spliterator(), entries);
				map.put(metric, new StatsSnapshotMetric(metric, entries));
			}
		}

		return map;
	}
	
	private LocalDateTime extractStartTime(JsonNode data) {
		return LocalDateTimeDeserializer.parse(data.get("start_time").asText());
	}
	
	private LocalDateTime extractEndTime(JsonNode data) {
		return LocalDateTimeDeserializer.parse(data.get("end_time").asText());
	}

	private void parseMetricEntries(StatsMetric metric, Spliterator<JsonNode> iterator, List<Object> entries) {
		Type metricType = mapOfMetrics.get(metric);
		if (metricType == BigDecimal.class) dumpEntriesAsDecimals(iterator, entries);
		else if (metricType == Integer.class) dumpEntriesAsIntegers(iterator, entries);
		else if (metricType == Double.class) dumpEntriesAsFloats(iterator, entries);
		else if (metricType == AbstractMap.SimpleEntry.class) dumpEntriesAsHashes(iterator, entries);
	}
	
	private void dumpEntriesAsDecimals(Spliterator<JsonNode> iterator, List<Object> entries) {
		iterator.forEachRemaining(i -> {
			String entryValue = i.asText();
			entries.add(BigDecimalMicroAmountDeserializer.parse(entryValue));
		});
	}
	
	private void dumpEntriesAsIntegers(Spliterator<JsonNode> iterator, List<Object> entries) {
		iterator.forEachRemaining(i -> {
			String entryValue = i.asText();
			entries.add(new Integer(entryValue));
		});
	}
	
	private void dumpEntriesAsFloats(Spliterator<JsonNode> iterator, List<Object> entries) {
		iterator.forEachRemaining(i -> {
			String entryValue = i.asText();
			entries.add(new Double(entryValue));
		});
	}
	
	private void dumpEntriesAsHashes(Spliterator<JsonNode> iterator, List<Object> entries) {
		iterator.forEachRemaining(i -> {
			i.fields().forEachRemaining(j -> {
				String key = j.getKey();
				String value = j.getValue().asText();
				entries.add(new AbstractMap.SimpleEntry<String, String>(key, value));
			});
		});
	}
}
