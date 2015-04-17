package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.CampaignQuery;
import org.springframework.social.twitter.api.advertising.CampaignSorting;
import org.springframework.social.twitter.api.impl.AbstractTwitterQueryForEntityBuilder;
import org.springframework.util.MultiValueMap;

public class CampaignQueryBuilder
	extends AbstractTwitterQueryForEntityBuilder<CampaignQuery, CampaignSorting>
	implements CampaignQuery {
	
	private List<String> fundingInstrumentIds; 
	private List<String> campaignIds;
	private Integer count;
	private Integer cursor;

	@Override
	public CampaignQuery withCampaigns(String... campaignIds) {
		this.campaignIds = new ArrayList<String>();
		for (int i = 0; i < campaignIds.length; i++)
			this.campaignIds.add(campaignIds[i]);
		return this;
	}

	@Override
	public CampaignQuery withFundingInstruments(String... fundingInstrumentIds) {
		this.fundingInstrumentIds = new ArrayList<String>();
		for (int i = 0; i < fundingInstrumentIds.length; i++)
			this.fundingInstrumentIds.add(fundingInstrumentIds[i]);
		return this;
	}

	@Override
	public CampaignQuery withCount(Integer count) {
		this.count = count;
		return this;
	}

	@Override
	public CampaignQuery withCursor(Integer cursor) {
		this.cursor = cursor;
		return this;
	}

	@Override
	protected void makeParameters(MultiValueMap<String, Object> map) {
		appendParameter(map, "campaign_ids", this.campaignIds);
		appendParameter(map, "funding_instrument_ids", this.fundingInstrumentIds);
		appendParameter(map, "count", this.count);
		appendParameter(map, "cursor", this.cursor);
	}
}
