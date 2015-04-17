package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForEntity;

public interface CampaignQuery extends TwitterQueryForEntity<CampaignQuery, CampaignSorting> {
	public CampaignQuery withCampaigns(String... campaignIds);
	public CampaignQuery withFundingInstruments(String... fundingInstrumentIds);
	public CampaignQuery withCount(Integer count);
	public CampaignQuery withCursor(Integer cursor);
}
