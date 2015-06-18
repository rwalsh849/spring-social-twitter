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
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvGenre;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvGenreQuery;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvMarket;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvMarketQuery;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvShow;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvShowQuery;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryOperations;
import org.springframework.social.twitter.api.impl.AbstractTwitterOperations;
import org.springframework.social.twitter.api.impl.DataListHolder;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForUri;
import org.springframework.social.twitter.api.impl.TwitterApiUriResourceForAdvertising;
import org.springframework.web.client.RestTemplate;

public class TargetingCriteriaDiscoveryTemplate extends AbstractTwitterOperations implements TargetingCriteriaDiscoveryOperations {
    private final RestTemplate restTemplate;

    public TargetingCriteriaDiscoveryTemplate(RestTemplate restTemplate, boolean isUserAuthorized, boolean isAppAuthorized) {
        super(isUserAuthorized, isAppAuthorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public DataListHolder<TargetingCriteriaDiscoveryForTvShow> tvShow(TargetingCriteriaDiscoveryForTvShowQuery query) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TARGETINGS_DISCOVERY_TV_SHOWS)
                        .withArgument(query.toQueryParameters())
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataListHolder<TargetingCriteriaDiscoveryForTvShow>>() {}
                ).getBody();
    }

    @Override
    public DataListHolder<TargetingCriteriaDiscoveryForTvMarket> tvMarkets(TargetingCriteriaDiscoveryForTvMarketQuery query) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TARGETINGS_DISCOVERY_TV_MARKETS)
                        .withArgument(query.toQueryParameters())
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataListHolder<TargetingCriteriaDiscoveryForTvMarket>>() {}
                ).getBody();
    }

    @Override
    public DataListHolder<TargetingCriteriaDiscoveryForTvGenre> tvGenres(TargetingCriteriaDiscoveryForTvGenreQuery query) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TARGETINGS_DISCOVERY_TV_GENRES)
                        .withArgument(query.toQueryParameters())
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataListHolder<TargetingCriteriaDiscoveryForTvGenre>>() {}
                ).getBody();
    }

}
