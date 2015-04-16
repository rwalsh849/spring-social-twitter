package org.springframework.social.twitter.api.impl;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RestRequestBodyBuilder {
	private final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	
	public RestRequestBodyBuilder withField(String name, String value) {
		map.set(name, value);
		return this;
	}
	
	public MultiValueMap<String, String> build() {
		return map;
	}
}
