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

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * Interface defining the creative operations.
 * 
 * @author Richard Walsh
 */
public interface CreativeOperations {
    /**
     * Retrieves a {@link Card} linked to a particular {@link AdvertisingAccount}.
     * 
     * @param accountId identifies the account for which we are attempting to get the campaign
     * @param id identifies the card
     * @return an instance of {@link Card}
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    Card getAppDownloadCard(String accountId, String id);
    
    /**
     * Retrieves a list of all {@link Card} linked to a particular {@link AdvertisingAccount}.
     * 
     * @param accountId identifies the account for which we are attempting to get the cards
     * @param query The query parameters that will filter the request
     * @return a list of {@link Card}
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    DataListHolder<Card> getCards(String accountId, CardQuery query);
    
    /**
     * Creates a {@link Card} for a {@link AdvertisingAccount} referenced by its 'accountId'.
     * 
     * @param accountId identifies the account for which we are attempting to create the card.
     * @param data is the request data builder that will generate the request body for the operation.
     * @return an instance of {@link Card} which refers to the card created in the procedure.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    Card createCard(String accountId, CardForm data);
    
    /**
     * Updates a {@link Card} for a {@link AdvertisingAccount} found by its cardId.
     * 
     * @param accountId identifies the account for which we are attempting to update the card.
     * @param id identifies the card that we wish to update.
     * @param data is the request data builder that will generate the request body for the operation.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void updateCard(String accountId, String id, CardForm data);
    
    /**
     * Deletes a {@link Card} related to an {@link AdvertisingAccount} found by its cardId.
     * 
     * @param accountId identifies the account of which card we wish to delete.
     * @param id identifies the card that we desire to delete.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void deleteCard(String accountId, String id);

}
