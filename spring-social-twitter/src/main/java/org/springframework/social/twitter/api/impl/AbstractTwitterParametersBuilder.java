package org.springframework.social.twitter.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.util.MultiValueMap;

public class AbstractTwitterParametersBuilder {
	private static final BigDecimal MICRO_MULTIPLIER = new BigDecimal(1000000);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static void appendParameter(
			MultiValueMap<String, Object> params, 
			String name,
			Object value) {
		
		if (value == null) return;
		if (value instanceof String && ((String) value).isEmpty()) return;
		if (value instanceof ArrayList && ((ArrayList) value).size() == 0) return;
		if (value instanceof ArrayList) {
			ArrayList valueAsList = (ArrayList) value;
			String[] valueArray = (String[]) valueAsList.stream().map(i -> i.toString()).toArray(size -> new String[size]);
			params.add(name, String.join(",", valueArray));
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
