/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.advertising;

import java.util.List;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.twitter.api.TwitterForm;

/**
 * Interface defining the operations for targeting criterias (Ads API).
 * 
 * @author Hudson Mendes
 */
public interface TargetingCriteriaOperations {

    /**
     * Retrieves a {@link TargetingCriteria} related to an {@link AdvertisingAccount} referred to by its id.
     * 
     * @param accountId identifies the account of which target criteria we wish to fetch.
     * @param id identifies the target criteria that will be retrieved.
     * @return an instance of {@link TargetingCriteria}
     */
    TargetingCriteria getTargetingCriteria(String accountId, String id);

    /**
     * Retrieves a list of {@link TargetingCriteria} related to an {@link AdvertisingAccount}.
     * 
     * @param accountId identifies the account for which we want to retrieve targeting criteria.
     * @param query The query parameters that will filter the request
     * @return a list of {@link TargetingCriteria}.
     */
    List<TargetingCriteria> getTargetingCriterias(String accountId, TargetingCriteriaQuery query);

    /**
     * Creates a {@link TargetingCriteria} related to an {@link AdvertisingAccount}
     * 
     * @param accountId identifies the account for which the targeting criteria will be created.
     * @param data defines the parameters that we shall use to generate the targeting criteria
     * @return an instance of {@link TargetingCriteria}
     */
    TargetingCriteria createTargetingCriteria(String accountId, TwitterForm data);

    /**
     * Deletes a {@link TargetingCriteria} related to an {@link AdvertisingAccount} found by its campaignId.
     * 
     * @param accountId identifies the account of which targeting criteria we wish to delete.
     * @param id identifies the targeting criteria that we desire to delete.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void deleteTargetingCriteria(String accountId, String id);

}
