package org.springframework.social.twitter.api.advertising;

import org.springframework.social.ApiException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.twitter.api.impl.DataListHolder;
import org.springframework.social.twitter.api.impl.TailoredAudienceForm;

/**
 * Interface defining the operations for targeting audience operations.
 * 
 * @author Hudson Mendes
 */
public interface TailoredAudienceOperations {

    /**
     * Retrieves a {@link TailoredAudience} linked to a particular {@link AdvertisingAccount} referred to by its id.
     * 
     * @param accountId identifies the account for which we wish to get the particular {@link TailoredAudience}.
     * @param id identifies which {@link TailoredAudience} we wish to retrieve.
     * @return an instance of {@link TailoredAudience}
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TailoredAudience getTailoredAudience(String accountId, String id);

    /**
     * Retrieves a list of all {@link TailoredAudience} linked to a particular {@link AdvertisingAccount}.
     * 
     * @param accountId identifies the account for which we want to get the {@link TailoredAudience}.
     * @param query The query parameters that will filter the request
     * @return a list of {@link TailoredAudience}
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    DataListHolder<TailoredAudience> getTailoredAudiences(String accountId, TailoredAudienceQuery query);

    /**
     * Creates {@link TailoredAudience} linked to a particular {@link AdvertisingAccount}.
     * 
     * @param accountId identifies the account for which we want to create a {@link TailoredAudience}.
     * @param input is the request data builder that will generate the request body for the operation.
     * @return an instance of {@link TailoredAudience}
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TailoredAudience createTailoredAudience(String accountId, TailoredAudienceForm input);

    /**
     * Updates {@link TailoredAudience} linked to a particular {@link AdvertisingAccount} referred to by its id.
     * 
     * @param accountId identifies the account for which we want to update a {@link TailoredAudience}.
     * @param id identifies which {@link TailoredAudience} we wish to update.
     * @param input is the request data builder that will generate the request body for the operation.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void updateTailoredAudience(String accountId, String id, TailoredAudienceForm input);

    /**
     * Deletes a {@link TailoredAudience} related to an {@link AdvertisingAccount} found by its campaignId.
     * 
     * @param accountId identifies the account of which {@link TailoredAudience} we wish to delete.
     * @param id identifies the {@link TailoredAudience} that we desire to delete.
     * @throws ApiException if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    void deleteTailoredAudience(String accountId, String id);
}
