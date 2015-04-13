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
package org.springframework.social.twitter.api.impl.advertising.deserializers;

import java.io.IOException;

import org.springframework.social.twitter.api.domain.models.advertising.StatisticalSnapshot;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserializes the complex object {@link StatisticalSnapshot}
 * Differently from the other REST objects in the domain, the {@link StatisticalSnapshot}
 * has a very fluent interface and changes according to the parameters passed to the endpoint.
 * 
 * This deserializer tackles this complexity by getting the flexible JSON parts and pushing them
 * into a rigit model {@link StatisticalSnapshotMetric} 
 * 
 * @author hudson
 *
 */
public class StatisticalSnapshotDeserializer extends JsonDeserializer<StatisticalSnapshot> {

	@Override
	public StatisticalSnapshot deserialize(JsonParser p,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		
		throw new UnsupportedOperationException("Not implemented");
	}

}
