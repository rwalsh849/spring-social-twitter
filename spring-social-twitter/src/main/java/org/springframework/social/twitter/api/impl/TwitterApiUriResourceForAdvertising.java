package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriResourceForAdvertising {
	ACCOUNTS("accounts"),
	
	CAMPAIGNS("accounts/:account_id/campaigns"),
	CAMPAIGN("accounts/:account_id/campaigns/:campaign_id"),
	
	FUNDING_INSTRUMENTS("accounts/:account_id/funding_instruments"),
	
	LINE_ITEMS("accounts/:account_id/line_items"),
	LINE_ITEM("accounts/:account_id/line_items/:line_item_id");
	
	private final String name;
	
	TwitterApiUriResourceForAdvertising(String path) {
		this.name = path;
	}
	
	public String getPath() {
		return this.name;
	}
}
