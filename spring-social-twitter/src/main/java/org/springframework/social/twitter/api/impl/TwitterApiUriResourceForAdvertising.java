package org.springframework.social.twitter.api.impl;

public enum TwitterApiUriResourceForAdvertising {
	ACCOUNTS("accounts"),
	
	CAMPAIGNS("accounts/:account_id/campaigns"),
	CAMPAIGN("accounts/:account_id/campaigns/:campaign_id"),
	
	FUNDING_INSTRUMENTS("accounts/:account_id/funding_instruments"),
	
	LINE_ITEMS("accounts/:account_id/line_items"),
	LINE_ITEM("accounts/:account_id/line_items/:line_item_id"),
	
	TARGETING_CRITERIAS("accounts/:account_id/targeting_criteria"),
	TARGETING_CRITERIA("accounts/:account_id/targeting_criteria/:targeting_criteria_id"),
	
	STATS_CAMPAIGNS("stats/accounts/:account_id/campaigns"),
	STATS_CAMPAIGN("stats/accounts/:account_id/campaigns/:campaign_id"),
	STATS_FUNDING_INSTRUMENTS("stats/accounts/:account_id/funding_instruments"),
	STATS_FUNDING_INSTRUMENT("stats/accounts/:account_id/funding_instruments/:funding_instrument_id"),
	STATS_LINE_ITEMS("stats/accounts/:account_id/line_items"),
	STATS_LINE_ITEM("stats/accounts/:account_id/line_items/:line_item_id");
	
	private final String name;
	
	TwitterApiUriResourceForAdvertising(String path) {
		this.name = path;
	}
	
	public String getPath() {
		return this.name;
	}
}
