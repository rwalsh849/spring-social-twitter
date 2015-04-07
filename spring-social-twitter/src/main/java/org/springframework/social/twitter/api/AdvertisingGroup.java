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
package org.springframework.social.twitter.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents an Advertising Group (also known as Line Item).
 * 
 * Source: https://dev.twitter.com/ads/campaigns 
 * 		Line items spend the budget defined by a campaign.
 * 		Line items pull together the per-engagement bid,
 * 		the Tweet or account to promote, and the targeting rules.
 * 
 * Source: https://dev.twitter.com/ads/reference/post/accounts/:account_id/line_items
 * 		Create a line item associated with the specified campaign belonging to the current account.
 * 		Note that for PROMOTED_ACCOUNT campaigns, associating a Promoted Tweet to the line_item will
 * 		add timeline placements on mobile in addition to the standard PROMOTED_ACCOUNT placement.
 * 
 * @author Hudson Mendes
 */
public class AdvertisingGroup extends TwitterObject {
	private final String id;
	private final String accountId;
	private final String campaignId;
	private final String currency;
	private final AdvertisingPlacementType placementType;
	private final AdvertisingObjetive objective;
	private final Optional<AdvertisingSentiment> includeSentiment;
	private final AdvertisingGroupOptimization optimization;
	private final BigDecimal totalBudgetAmount;
	private final BigDecimal bidAmount;
	private final Boolean paused;
	private final Boolean deleted; 
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	
	public AdvertisingGroup(
			String id, String accountId, String campaignId, String currency,
			AdvertisingPlacementType placementType, AdvertisingObjetive objective,
			Optional<AdvertisingSentiment> includeSentiment, AdvertisingGroupOptimization optimization,
			BigDecimal totalBudgetAmount, BigDecimal bidAmount,
			Boolean paused, Boolean deleted,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		
		this.id = id;
		this.accountId = accountId;
		this.campaignId = campaignId;
		this.currency = currency;
		
		this.placementType = placementType;
		this.objective = objective;
		this.includeSentiment = includeSentiment;
		this.optimization = optimization;
		
		this.totalBudgetAmount = totalBudgetAmount;
		this.bidAmount = bidAmount;
		
		this.paused = paused;
		this.deleted = deleted;
		
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public String getCurrency() {
		return currency;
	}

	public AdvertisingPlacementType getPlacementType() {
		return placementType;
	}

	public AdvertisingObjetive getObjective() {
		return objective;
	}

	public Optional<AdvertisingSentiment> getIncludeSentiment() {
		return includeSentiment;
	}

	public AdvertisingGroupOptimization getOptimization() {
		return optimization;
	}

	public BigDecimal getTotalBudgetAmount() {
		return totalBudgetAmount;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public Boolean getPaused() {
		return paused;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
