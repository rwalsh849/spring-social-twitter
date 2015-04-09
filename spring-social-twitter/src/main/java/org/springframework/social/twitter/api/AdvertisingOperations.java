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

import java.util.List;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;

/**
 * Interface defining the operations for advertising operations.
 * @author Hudson Mendes
 */
public interface AdvertisingOperations {
	
	/**
	 * Retrieves a list of all {@link AdvertisingAccount} to which the authenticating user has access.  
	 * @return a list of {@link AdvertisingAccount}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<AdvertisingAccount> getAccounts();
	
	/**
	 * Retrieves a {@link Campaign} linked to a particular {@link AdvertisingAccount}.  
	 * @return an instance of {@link Campaign}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	Campaign getCampaign(String accountId, String id);
	
	/**
	 * Retrieves a list of all {@link Campaign} linked to a particular {@link AdvertisingAccount}.  
	 * @return a list of {@link Campaign}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<Campaign> getCampaigns(String accountId);
	
	/**
	 * Creates a {@link Campaign} for a {@link AdvertisingAccount} referenced by its 'accountId'.
	 * @return an instance of {@link Campaign} which refers to the campaign created in the procedure.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	Campaign createCampaign(String accountId, TransferingData data);
	
	/**
	 * Updates a {@link Campaign} for a {@link AdvertisingAccount} found by its campaignId.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void updateCampaign(String accountId, String id, TransferingData data);
	
	/**
	 * Deletes a {@link Campaign} related to an {@link AdvertisingAccount} found by its campaignId.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void deleteCampaign(String accountId, String id);
	
	/**
	 * Retrieves a list of all {@link FundingInstrument} linked to a particular {@link AdvertisingAccount}.  
	 * @return a list of {@link FundingInstrument}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<FundingInstrument> getFundingInstruments(String accountId);
	
	/**
	 * Retrieves a list of all {@link LineItem} linked to a particular {@link AdvertisingAccount}.  
	 * @return a list of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<LineItem> getLineItems(String accountId);
	
	/**
	 * Retrieves a {@link LineItem} linked to a particular {@link AdvertisingAccount} referred to by its id.  
	 * @return an instance of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	LineItem getLineItem(String accountId, String id);
	
	/**
	 * Creates {@link LineItem} linked to a particular {@link AdvertisingAccount}.  
	 * @return an instance of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	LineItem createLineItem(String accountId, TransferingData data); 
}
