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
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvShow;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;
import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * @author Hudson Mendes
 */
public class TargetingCriteriaDeliveryTemplateTest extends AbstractTwitterApiTest {

    @Test
    public void tvShows() {
        String mockedAccountId = "hkk5";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/targeting_criteria/tv_shows?tv_market_locale=pt_BR"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-targetings-tv_shows"), APPLICATION_JSON));

        DataListHolder<TargetingCriteriaDiscoveryForTvShow> discoveries = twitter.targetingCriteriaDiscoveryOperations().tvShow(
                mockedAccountId,
                new TargetingCriteriaDiscoveryForTvShowQueryBuilder()
                        .withLocale(Locale.forLanguageTag("pt-BR")));

        assertTvShowsDiscoveries(discoveries.getList());
    }

    private void assertTvShowsDiscoveries(List<TargetingCriteriaDiscoveryForTvShow> actual) {
        Assert.assertEquals(2, actual.size());

        Assert.assertEquals(actual.get(0).getId(), new Long("10002546242"));
        Assert.assertEquals(actual.get(0).getEstimatedUsers(), new Long("154958"));
        Assert.assertEquals(actual.get(0).getName(), "BBC World News");
        Assert.assertEquals(actual.get(0).getGenre(), "TALK");

        Assert.assertEquals(actual.get(1).getId(), new Long("10000283242"));
        Assert.assertEquals(actual.get(1).getEstimatedUsers(), new Long("93330"));
        Assert.assertEquals(actual.get(1).getName(), "E! News");
        Assert.assertEquals(actual.get(1).getGenre(), "TALK");
    }
}
