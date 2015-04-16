package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForStats;

public interface StatisticsOfFundingInstrumentQuery extends TwitterQueryForStats<StatisticsOfFundingInstrumentQuery> {

	public abstract StatisticsOfFundingInstrumentQuery withFundingInstruments(
			String... fundingInstrumentIds);

}