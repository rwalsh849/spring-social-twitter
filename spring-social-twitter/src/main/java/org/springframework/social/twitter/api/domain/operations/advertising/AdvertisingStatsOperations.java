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
package org.springframework.social.twitter.api.domain.operations.advertising;

import org.springframework.social.twitter.api.domain.models.QueryingData;
import org.springframework.social.twitter.api.domain.models.advertising.Campaign;
import org.springframework.social.twitter.api.domain.models.advertising.StatisticalSnapshot;


/**
 * Interface defining the operations for advertising statistical operations.
 * @author Hudson Mendes
 */
public interface AdvertisingStatsOperations {
	
	/**
	 * Snapshot of Advertising Statistics for Multiple {@link Campaign}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param campaignId The id of the campaign for which we which to retrieve the statistics.
	 * @return
	 */
	StatisticalSnapshot byCampaign(String accountId, QueryingData query);
	
	/**
	 * Snapshot of Advertising Statistics by {@link Campaign}.
	 * @param accountId The id of the account for which we want to retrieve the statistics.
	 * @param campaignId The id of the campaign for which we which to retrieve the statistics.
	 * @return
	 */
	StatisticalSnapshot byCampaign(String accountId, String campaignId, QueryingData query);
}
