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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvGenre;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvMarket;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvShow;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;
import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * @author Hudson Mendes
 */
public class TargetingCriteriaDiscoveryTemplateTest extends AbstractTwitterApiTest {

    @Test
    public void tvShows() {
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/targeting_criteria/tv_shows?tv_market_locale=pt-BR"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-targetings-tv_shows"), APPLICATION_JSON));

        DataListHolder<TargetingCriteriaDiscoveryForTvShow> discoveries = twitter.targetingCriteriaDiscoveryOperations().tvShow(
                new TargetingCriteriaDiscoveryForTvShowQueryBuilder()
                        .withLocale("pt-BR"));

        assertTvShowsDiscoveries(discoveries.getList());
    }

    private void assertTvShowsDiscoveries(List<TargetingCriteriaDiscoveryForTvShow> actual) {
        Assert.assertEquals(50, actual.size());

        Assert.assertEquals(new Long("10032876335"), actual.get(0).getId());
        Assert.assertEquals(new Long("1000"), actual.get(0).getEstimatedUsers());
        Assert.assertEquals("Debate 2014 - Presidente", actual.get(0).getName());
        Assert.assertEquals("SPECIAL", actual.get(0).getGenre());

        Assert.assertEquals(new Long("10032994279"), actual.get(1).getId());
        Assert.assertEquals(new Long("1000"), actual.get(1).getEstimatedUsers());
        Assert.assertEquals("2014 FIBA World Cup", actual.get(1).getName());
        Assert.assertEquals("SPORTS", actual.get(1).getGenre());
    }

    @Test
    public void tvMarkets() {
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/targeting_criteria/tv_markets"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-targetings-tv_markets"), APPLICATION_JSON));

        DataListHolder<TargetingCriteriaDiscoveryForTvMarket> discoveries = twitter.targetingCriteriaDiscoveryOperations().tvMarkets(
                new TargetingCriteriaDiscoveryForTvMarketQueryBuilder());

        assertTvMarketsDiscoveries(discoveries.getList());
    }

    private void assertTvMarketsDiscoveries(List<TargetingCriteriaDiscoveryForTvMarket> actual) {
        Assert.assertEquals(17, actual.size());

        Assert.assertEquals("6", actual.get(0).getId());
        Assert.assertEquals("France", actual.get(0).getName());
        Assert.assertEquals("FR", actual.get(0).getCountryCode());
        Assert.assertEquals("fr-FR", actual.get(0).getLocale());

        Assert.assertEquals("i", actual.get(1).getId());
        Assert.assertEquals("Chile", actual.get(1).getName());
        Assert.assertEquals("CL", actual.get(1).getCountryCode());
        Assert.assertEquals("es-CL", actual.get(1).getLocale());
    }

    @Test
    public void tvGenres() {
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/targeting_criteria/tv_genres"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-targetings-tv_genres"), APPLICATION_JSON));

        DataListHolder<TargetingCriteriaDiscoveryForTvGenre> discoveries = twitter.targetingCriteriaDiscoveryOperations().tvGenres(
                new TargetingCriteriaDiscoveryForTvGenreQueryBuilder());

        assertTvGenresDiscoveries(discoveries.getList());
    }

    private void assertTvGenresDiscoveries(List<TargetingCriteriaDiscoveryForTvGenre> actual) {
        Assert.assertEquals(20, actual.size());

        Assert.assertEquals(new Long("2"), actual.get(0).getId());
        Assert.assertEquals("COMEDY", actual.get(0).getName());

        Assert.assertEquals(new Long("11"), actual.get(1).getId());
        Assert.assertEquals("SPORTS", actual.get(1).getName());
    }
}
