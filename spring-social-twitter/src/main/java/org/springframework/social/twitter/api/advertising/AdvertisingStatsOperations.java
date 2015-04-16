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
package org.springframework.social.twitter.api.advertising;

import java.util.List;

import org.springframework.social.twitter.api.impl.basic.Tweet;


/**
 * Interface defining the operations for advertising statistical operations.
 * @author Hudson Mendes
 */
public interface AdvertisingStatsOperations {
	
	/**
	 * Snapshot of Advertising Statistics for Multiple {@link AdvertisingAccount}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byAccounts(String accountId, StatsOfAccountQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for Multiple {@link Campaign}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byCampaigns(String accountId, StatsOfCampaignQuery query);
	
	/**
	 * Snapshot of Advertising Statistics by {@link Campaign}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param campaignId The id of the campaign for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return an instance of {@link StatsSnapshot}
	 */
	StatsSnapshot byCampaign(String accountId, String campaignId, StatsOfCampaignQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for Multiple {@link FundingInstrument}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics. 
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byFundingInstruments(String accountId, StatsOfFundingInstrumentQuery query);
	
	/**
	 * Snapshot of Advertising Statistics by {@link Campaign}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param fundingInstrumentId The id of the funding instrument for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return an instance of {@link StatsSnapshot}
	 */
	StatsSnapshot byFundingInstrument(String accountId, String fundingInstrumentId, StatsOfFundingInstrumentQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for Multiple {@link LineItem}. 
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byLineItems(String accountId, StatsOfLineItemQuery query);
	
	/**
	 * Snapshot of Advertising Statistics by {@link LineItem}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param lineItemId The id of the line item for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return an instance of {@link StatsSnapshot}
	 */
	StatsSnapshot byLineItem(String accountId, String lineItemId, StatsOfLineItemQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for Multiple promoted accounts.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byPromotedAccounts(String accountId, StatsOfPromotedAccountQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for a promoted accounts.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param promotedAccountId The id of the promoted account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return an instance of {@link StatsSnapshot}
	 */
	StatsSnapshot byPromotedAccount(String accountId, String promotedAccountId, StatsOfPromotedAccountQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for Multiple promoted {@link Tweet}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return a list of {@link StatsSnapshot}
	 */
	List<StatsSnapshot> byPromotedTweets(String accountId, StatsOfPromotedTweetQuery query);
	
	/**
	 * Snapshot of Advertising Statistics for a promoted {@link Tweet}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param promotedTweetId The id of the promoted tweet for which we want to retrieve the statistics.
	 * @param query The query for which we shall retrieve statistics.
	 * @return an instance of {@link StatsSnapshot}
	 */
	StatsSnapshot byPromotedTweet(String accountId, String promotedTweetId, StatsOfPromotedTweetQuery query);
}
