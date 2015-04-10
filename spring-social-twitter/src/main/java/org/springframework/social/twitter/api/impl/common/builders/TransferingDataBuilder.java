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
package org.springframework.social.twitter.api.impl.common.builders;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.social.twitter.api.common.models.TransferingData;
import org.springframework.util.MultiValueMap;

/**
 * Builder base for all other builders which have the intent of
 * POSTing or PUTting data through an api endpoint, transforming
 * them in to a body Map of values.
 * 
 * @author Hudson Mendes
 */
public abstract class TransferingDataBuilder implements TransferingData {
	private static final BigDecimal MICRO_MULTIPLIER = new BigDecimal(1000000);
	
	public abstract MultiValueMap<String, Object> toRequestParameters();
	
	@SuppressWarnings("rawtypes")
	protected static void appendParameter(
			MultiValueMap<String, Object> params, 
			String name,
			Object value) {
		
		if (value == null) return;
		if (value instanceof String && ((String) value).isEmpty()) return;
		if (value instanceof ArrayList && ((ArrayList) value).size() == 0) return;
		if (value instanceof ArrayList) {
			for (int i = 0; i < Array.getLength(value); i++) {
				params.add(name, Array.get(value, i));
			}
		}
		else {
			params.set(name, value.toString());
		}
			
	}
	
	protected static Long translateBigDecimalIntoMicro(BigDecimal value) {
		if (value == null) return new Long(0);
		return value.multiply(MICRO_MULTIPLIER).longValue();
	}
}
