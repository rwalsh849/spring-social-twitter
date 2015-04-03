package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriAdCampaignResource {
	ACCOUNT("accounts"),
	CAMPAIGN("accounts/:account_id/campaigns");
	
	private final String name;
	
	TwitterApiUriAdCampaignResource(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return this.name;
	}
}
