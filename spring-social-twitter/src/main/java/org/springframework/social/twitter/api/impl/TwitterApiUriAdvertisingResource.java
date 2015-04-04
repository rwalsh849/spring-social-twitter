package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriAdvertisingResource {
	ACCOUNT("accounts"),
	CAMPAIGN("accounts/:account_id/campaigns");
	
	private final String name;
	
	TwitterApiUriAdvertisingResource(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return this.name;
	}
}
