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
package org.springframework.social.twitter.api.impl.advertising.mixins;

import java.time.LocalDateTime;

import org.springframework.social.twitter.api.domain.models.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.domain.models.advertising.TargetingType;
import org.springframework.social.twitter.api.impl.common.deserializers.LocalDateTimeDeserializer;
import org.springframework.social.twitter.api.impl.standard.mixins.TwitterObjectMixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Mixin class for adding Jackson annotations to {@link TargetingCriteria}.
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TargetingCriteriaMixin extends TwitterObjectMixin {
	
	@JsonCreator
	TargetingCriteriaMixin(
			@JsonProperty("id") String id,
			@JsonProperty("account_id") String accountId,
			@JsonProperty("line_item_id") String lineItemId,
			@JsonProperty("name") String name,
			@JsonProperty("targeting_type") TargetingType targetingType,
			@JsonProperty("targeting_value") String targetingValue,
			@JsonProperty("deleted") Boolean deleted,
			@JsonProperty("created_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime createdAt,
			@JsonProperty("updated_at") @JsonDeserialize(using=LocalDateTimeDeserializer.class) LocalDateTime updatedAt) {}
	
	
}
