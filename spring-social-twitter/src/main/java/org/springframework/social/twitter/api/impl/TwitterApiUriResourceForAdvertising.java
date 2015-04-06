package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriResourceForAdvertising {
	ACCOUNT("accounts"),
	CAMPAIGN("accounts/:account_id/campaigns");
	
	private final String name;
	
	TwitterApiUriResourceForAdvertising(String path) {
		this.name = path;
	}
	
	public String getPath() {
		return this.name;
	}
}
