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
import java.util.List;

import org.springframework.social.twitter.api.AdCampaign;
import org.springframework.social.twitter.api.ReasonNotServable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Mixin class for adding Jackson annotations to {@link AdCampaign}.
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdCampaignMixin extends TwitterObjectMixin {
	
	@JsonCreator
	AdCampaignMixin(
			@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("account_id") String accountId,
			@JsonProperty("currency") String currency,
			@JsonProperty("funding_instrument_id") String fundingInstrumentId,
			@JsonProperty("total_budget_amount_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal totalBudget,
			@JsonProperty("daily_budget_amount_local_micro") @JsonDeserialize(using=BigDecimalMicroAmountDeserializer.class) BigDecimal dailyBudget,
			@JsonProperty("start_time") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime startTime,
			@JsonProperty("end_time") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime endTime,
			@JsonProperty("created_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime createdAt,
			@JsonProperty("updated_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime updatedAt,
			@JsonProperty("reasons_not_servable") List<ReasonNotServable> reasonsNotServable,
			@JsonProperty("standard_delivery") Boolean standardDelivery,
			@JsonProperty("paused") Boolean paused,
			@JsonProperty("servable") Boolean servable,
			@JsonProperty("deleted") Boolean deleted) {}
}
