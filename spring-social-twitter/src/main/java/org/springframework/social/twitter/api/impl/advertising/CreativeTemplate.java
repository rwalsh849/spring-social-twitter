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
package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.advertising.AdvertisingOperations;
import org.springframework.social.twitter.api.advertising.Campaign;
import org.springframework.social.twitter.api.advertising.Card;
import org.springframework.social.twitter.api.advertising.CardForm;
import org.springframework.social.twitter.api.advertising.CardQuery;
import org.springframework.social.twitter.api.advertising.CreativeOperations;
import org.springframework.social.twitter.api.impl.AbstractTwitterOperations;
import org.springframework.social.twitter.api.impl.DataListHolder;
import org.springframework.social.twitter.api.impl.DataSingleHolder;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForHttpEntity;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForUri;
import org.springframework.social.twitter.api.impl.TwitterApiUriResourceForAdvertising;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AdvertisingOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * 
 * @author Richard Walsh
 */
public class CreativeTemplate extends AbstractTwitterOperations implements CreativeOperations {
    private final RestTemplate restTemplate;

    public CreativeTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
        super(isAuthorizedForUser, isAuthorizedForApp);
        this.restTemplate = restTemplate;
    }
    
    @Override
    public Card getAppDownloadCard(String accountId, String id) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.CREATIVE_APP_DOWNLOAD_CARD)
                        .withArgument("account_id", accountId)
                        .withArgument("card_id", id)
                        .withArgument("with_deleted", true)
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataSingleHolder<Card>>() {}
                ).getBody().getData();
    }

    @Override
    public DataListHolder<Card> getCards(String accountId, CardQuery query) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.CREATIVE_APP_DOWNLOAD_CARDS)
                        .withArgument("account_id", accountId)
                        .withArgument(query.toQueryParameters())
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataListHolder<Card>>() {}
                ).getBody();
    }

    @Override
    public Card createCard(String accountId, CardForm data) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.CREATIVE_APP_DOWNLOAD_CARD_CREATE)
                        .withArgument("account_id", accountId)
                        .build(),
                HttpMethod.POST,
                new TwitterApiBuilderForHttpEntity<>(data.toRequestBody()).build(),
                new ParameterizedTypeReference<DataSingleHolder<Card>>() {}
                ).getBody().getData();
    }

    @Override
    public void updateCard(String accountId, String id, CardForm data) {
        requireUserAuthorization();
        restTemplate.put(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.CREATIVE_APP_DOWNLOAD_CARD_UPDATE)
                        .withArgument("account_id", accountId)
                        .withArgument("card_id", id)
                        .build(),
                data.toRequestBody());
    }

    @Override
    public void deleteCard(String accountId, String id) {
        requireUserAuthorization();
        restTemplate.delete(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.CREATIVE_APP_DOWNLOAD_CARD_DELETE)
                        .withArgument("account_id", accountId)
                        .withArgument("card_id", id)
                        .build());
    }

}
