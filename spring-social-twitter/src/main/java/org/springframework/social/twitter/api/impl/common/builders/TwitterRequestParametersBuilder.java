package org.springframework.social.twitter.api.impl.common.builders;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.util.MultiValueMap;

public class TwitterRequestParametersBuilder {
	private static final BigDecimal MICRO_MULTIPLIER = new BigDecimal(1000000);
	
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
