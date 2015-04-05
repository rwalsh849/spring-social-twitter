package org.springframework.social.twitter.api.impl;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		/**
		 * Very ugly fix to get the damn date in ISO format working
		 * I'm still trying to figure out a better solution. 
		 */
		String raw = p.getText();
		Instant instant = Instant.parse(raw);
		ZoneId zone = ZoneId.of("UTC");
		return LocalDateTime.ofInstant(instant, zone);
	}

}
