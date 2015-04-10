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
package org.springframework.social.twitter.api.common.operations;

import java.util.List;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.twitter.api.common.models.TransferingData;
import org.springframework.social.twitter.api.common.models.advertising.AdvertisingAccount;
import org.springframework.social.twitter.api.common.models.advertising.Campaign;
import org.springframework.social.twitter.api.common.models.advertising.FundingInstrument;
import org.springframework.social.twitter.api.common.models.advertising.LineItem;
import org.springframework.social.twitter.api.common.models.advertising.TargetingCriteria;

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
	 * @param accountId identifies the account for which we are attempting to get the campaign
	 * @param id identifies the campaign  
	 * @return an instance of {@link Campaign}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	Campaign getCampaign(String accountId, String id);
	
	/**
	 * Retrieves a list of all {@link Campaign} linked to a particular {@link AdvertisingAccount}.
	 * @param accountId identifies the account for which we are attempting to get the campaigns  
	 * @return a list of {@link Campaign}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<Campaign> getCampaigns(String accountId);
	
	/**
	 * Creates a {@link Campaign} for a {@link AdvertisingAccount} referenced by its 'accountId'.
	 * @param accountId identifies the account for which we are attempting to create the campaign.
	 * @param data is the request data builder that will generate the request body for the operation.
	 * @return an instance of {@link Campaign} which refers to the campaign created in the procedure.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	Campaign createCampaign(String accountId, TransferingData data);
	
	/**
	 * Updates a {@link Campaign} for a {@link AdvertisingAccount} found by its campaignId.
	 * @param accountId identifies the account for which we are attempting to update the campaign.
	 * @param id identifies the campaign that we wish to update.
	 * @param data is the request data builder that will generate the request body for the operation. 
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void updateCampaign(String accountId, String id, TransferingData data);
	
	/**
	 * Deletes a {@link Campaign} related to an {@link AdvertisingAccount} found by its campaignId.
	 * @param accountId identifies the account of which campaign we wish to delete.
	 * @param id identifies the campaign that we desire to delete.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void deleteCampaign(String accountId, String id);
	
	/**
	 * Retrieves a list of all {@link FundingInstrument} linked to a particular {@link AdvertisingAccount}.
	 * @param accountId identifies the account for which we want to get the funding instruments.  
	 * @return a list of {@link FundingInstrument}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<FundingInstrument> getFundingInstruments(String accountId);
	
	/**
	 * Retrieves a list of all {@link LineItem} linked to a particular {@link AdvertisingAccount}.
	 * @param accountId identifies the account for which we want to get the line items.  
	 * @return a list of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	List<LineItem> getLineItems(String accountId);
	
	/**
	 * Retrieves a {@link LineItem} linked to a particular {@link AdvertisingAccount} referred to by its id.
	 * @param accountId identifies the account for which we wish to get the particular line item.
	 * @param id identifies which line id we wish to retrieve.   
	 * @return an instance of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	LineItem getLineItem(String accountId, String id);
	
	/**
	 * Creates {@link LineItem} linked to a particular {@link AdvertisingAccount}.
	 * @param accountId identifies the account for which we want to create a line item.
	 * @param data is the request data builder that will generate the request body for the operation.   
	 * @return an instance of {@link LineItem}
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	LineItem createLineItem(String accountId, TransferingData data);
	
	/**
	 * Updates {@link LineItem} linked to a particular {@link AdvertisingAccount} referred to by its id.
	 * @param accountId identifies the account for which we want to update a line item.
	 * @param id identifies which line id we wish to update.
	 * @param data is the request data builder that will generate the request body for the operation.  
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void updateLineItem(String accountId, String id, TransferingData data);
	
	/**
	 * Deletes a {@link LineItem} related to an {@link AdvertisingAccount} found by its campaignId.
	 * @param accountId identifies the account of which line item we wish to delete.
	 * @param id identifies the line item that we desire to delete.
	 * @throws ApiException if there is an error while communicating with Twitter.
	 * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
	 */
	void deleteLineItem(String accountId, String id);
	
	/**
	 * Retrieves a list of {@link TargetingCriteria} related to an {@link AdvertisingAccount}. 
	 * @param accountId identifies the account for which we want to retrieve targeting criteria.
	 * @return a list of {@link TargetingCriteria}. 
	 */
	List<TargetingCriteria> getTargetingCriterias(String accountId);
	
	/**
	 * Retrieves a {@link TargetingCriteria} related to an {@link AdvertisingAccount} referred to by its id.
	 * @param accountId identifies the account of which target criteria we wish to fetch. 
	 * @param id identifies the target criteria that will be retrieved.
	 * @return an instance of {@link TargetingCriteria}
	 */
	TargetingCriteria getTargetingCriteria(String accountId, String id);
	
	/**
	 * Creates a {@link TargetingCriteria} related to an {@link AdvertisingAccount}
	 * @param accountId identifies the account for which the targeting criteria will be created.
	 * @param data defines the parameters that we shall use to generate the targeting criteria  
	 * @return an instance of {@link TargetingCriteria}
	 */
	TargetingCriteria createTargetingCriteria(String accountId, TransferingData data);
}
