package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriAdvertisingResource {
	ACCOUNT("accounts"),
	CAMPAIGN("accounts/:account_id/campaigns");
	
	private final String name;
	
	TwitterApiUriAdvertisingResource(String path) {
		this.name = path;
	}
	
	public String getPath() {
		return this.name;
	}
}
