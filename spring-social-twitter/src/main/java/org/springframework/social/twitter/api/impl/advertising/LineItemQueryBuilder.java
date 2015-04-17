package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.LineItemQuery;
import org.springframework.social.twitter.api.advertising.LineItemSorting;
import org.springframework.social.twitter.api.impl.AbstractTwitterQueryForEntityBuilder;
import org.springframework.util.MultiValueMap;

public class LineItemQueryBuilder
	extends AbstractTwitterQueryForEntityBuilder<LineItemQuery, LineItemSorting>
	implements LineItemQuery {

	private List<String> fundingInstrumentIds; 
	private List<String> campaignIds;
	private List<String> lineItemIds;
	private Integer count;
	private Integer cursor;

	@Override
	public LineItemQuery withCampaigns(String... campaignIds) {
		this.campaignIds = new ArrayList<String>();
		for (int i = 0; i < campaignIds.length; i++)
			this.campaignIds.add(campaignIds[i]);
		return this;
	}

	@Override
	public LineItemQuery withFundingInstruments(String... fundingInstrumentIds) {
		this.fundingInstrumentIds = new ArrayList<String>();
		for (int i = 0; i < fundingInstrumentIds.length; i++)
			this.fundingInstrumentIds.add(fundingInstrumentIds[i]);
		return this;
	}

	@Override
	public LineItemQuery withLineItems(String... lineItemIds) {
		this.lineItemIds = new ArrayList<String>();
		for (int i = 0; i < lineItemIds.length; i++)
			this.lineItemIds.add(lineItemIds[i]);
		return this;
	}
	
	
	@Override
	public LineItemQuery withCount(Integer count) {
		this.count = count;
		return this;
	}

	@Override
	public LineItemQuery withCursor(Integer cursor) {
		this.cursor = cursor;
		return this;
	}

	@Override
	protected void makeParameters(MultiValueMap<String, Object> map) {
		appendParameter(map, "campaign_ids", this.campaignIds);
		appendParameter(map, "funding_instrument_ids", this.fundingInstrumentIds);
		appendParameter(map, "line_item_ids", this.lineItemIds);
		appendParameter(map, "count", this.count);
		appendParameter(map, "cursor", this.cursor);
	}

}
