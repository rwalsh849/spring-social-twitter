package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.FundingInstrumentQuery;
import org.springframework.social.twitter.api.advertising.FundingInstrumentSorting;
import org.springframework.social.twitter.api.impl.AbstractTwitterQueryForEntityBuilder;
import org.springframework.util.MultiValueMap;

public class FundingInstrumentQueryBuilder
	extends AbstractTwitterQueryForEntityBuilder<FundingInstrumentQuery, FundingInstrumentSorting>
	implements FundingInstrumentQuery {
	
	private List<String> fundingInstrumentIds; 
	
	public FundingInstrumentQuery withFundingInstruments(String... fundingInstrumentIds) {
		this.fundingInstrumentIds = new ArrayList<String>();
		for (int i = 0; i < fundingInstrumentIds.length; i++)
			this.fundingInstrumentIds.add(fundingInstrumentIds[i]);
		return this;
	}

	@Override
	protected void makeParameters(MultiValueMap<String, Object> map) {
		appendParameter(map, "funding_instrument_ids", this.fundingInstrumentIds);
	}

}
