/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.LineItem;
import org.springframework.social.twitter.api.advertising.LineItemQuery;
import org.springframework.social.twitter.api.advertising.LineItemSorting;
import org.springframework.social.twitter.api.impl.AbstractTwitterQueryForEntityBuilder;
import org.springframework.util.MultiValueMap;

/**
 * Facilitates the creation of the query that will be
 * used to filter results from the {@link LineItem} endpoint.
 * 
 * @author Hudson Mendes
 */
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
