package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatsOfFundingInstrumentQuery extends TwitterQueryForStats<StatsOfFundingInstrumentQuery> {

	public abstract StatsOfFundingInstrumentQuery withFundingInstruments(
			String... fundingInstrumentIds);

}