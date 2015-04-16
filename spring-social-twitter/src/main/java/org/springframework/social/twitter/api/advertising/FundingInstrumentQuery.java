package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForEntity;

public interface FundingInstrumentQuery extends TwitterQueryForEntity<FundingInstrumentQuery, FundingInstrumentSorting> {
	public FundingInstrumentQuery withFundingInstruments(String... fundingInstrumentIds);
}
