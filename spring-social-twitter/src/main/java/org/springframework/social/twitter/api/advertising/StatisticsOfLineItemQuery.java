package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatisticsOfLineItemQuery extends TwitterQueryForStats<StatisticsOfLineItemQuery> {

	public abstract StatisticsOfLineItemQuery withLineItems(
			String... lineItemIds);

}