package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatsOfCampaignQuery extends TwitterQueryForStats<StatsOfCampaignQuery> {

	public abstract StatsOfCampaignQuery withCampaigns(
			String... campaignIds);

}