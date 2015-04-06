package org.springframework.social.twitter.api.impl;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BigDecimalMicroAmountDeserializer extends JsonDeserializer<BigDecimal> {

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		Long microAmount = new Long(p.getText());
		return new BigDecimal(microAmount / 1000000);
	}

}
