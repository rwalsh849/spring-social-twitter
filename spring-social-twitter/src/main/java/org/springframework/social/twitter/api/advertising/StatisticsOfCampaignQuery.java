package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatisticsOfCampaignQuery extends TwitterQueryForStats<StatisticsOfCampaignQuery> {

	public abstract StatisticsOfCampaignQuery withCampaigns(
			String... campaignIds);

}