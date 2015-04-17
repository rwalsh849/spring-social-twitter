package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForEntity;

public interface LineItemQuery extends TwitterQueryForEntity<LineItemQuery, LineItemSorting> {
	public LineItemQuery withCampaigns(String... campaignIds);
	public LineItemQuery withFundingInstruments(String... fundingInstrumentIds);
	public LineItemQuery withLineItems(String... lineItemIds);
	public LineItemQuery withCount(Integer count);
	public LineItemQuery withCursor(Integer cursor);
}
