package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatsOfLineItemQuery extends TwitterQueryForStats<StatsOfLineItemQuery> {

	public abstract StatsOfLineItemQuery withLineItems(
			String... lineItemIds);

}