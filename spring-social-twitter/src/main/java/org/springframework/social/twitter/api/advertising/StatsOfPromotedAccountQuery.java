package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatsOfPromotedAccountQuery extends TwitterQueryForStats<StatsOfPromotedAccountQuery> {

	public abstract StatsOfPromotedAccountQuery withPromotedAccounts(
			String... promotedAccountIds);

}