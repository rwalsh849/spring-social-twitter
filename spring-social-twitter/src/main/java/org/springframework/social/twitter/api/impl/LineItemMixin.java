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
package org.springframework.social.twitter.api.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.social.twitter.api.AdvertisingObjetive;
import org.springframework.social.twitter.api.AdvertisingPlacementType;
import org.springframework.social.twitter.api.AdvertisingSentiment;
import org.springframework.social.twitter.api.LineItem;
import org.springframework.social.twitter.api.LineItemOptimization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Mixin class for adding Jackson annotations to {@link LineItem}.
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class LineItemMixin extends TwitterObjectMixin {

	@JsonCreator
	LineItemMixin(
			@JsonProperty("id") String id,
			@JsonProperty("account_id") String accountId,
			@JsonProperty("campaign_id") String campaignId,
			@JsonProperty("currency") String currency,
			@JsonProperty("placement_type") AdvertisingPlacementType placementType,
			@JsonProperty("objective") AdvertisingObjetive objective,
			@JsonProperty("include_sentiment") AdvertisingSentiment includeSentiment,
			@JsonProperty("optimization") LineItemOptimization optimization,
			@JsonProperty("total_buget_amount_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal totalBudgetAmount,
			@JsonProperty("bid_amount_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal bidAmount,
			@JsonProperty("suggested_high_cpe_bid_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal suggestedHighCpeBid,
			@JsonProperty("suggested_low_cpe_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal suggestedLowCpeBid,
			@JsonProperty("paused") Boolean paused,
			@JsonProperty("deleted") Boolean deleted,
			@JsonProperty("created_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime createdAt,
			@JsonProperty("updated_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime updatedAt) {}
	
}
