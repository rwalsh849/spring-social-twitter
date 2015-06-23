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

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;

/**
 * @author Hudson mendes
 */
public class PromotedTweetTemplateTest extends AbstractTwitterApiTest {

    @Test
    public void createPromotedOnlyTweet() {
        String mockedAccountId = "hkk5";

        String chainedPostContent = "";

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tweet"))
                .andExpect(method(POST))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-promoted-only-tweet"), APPLICATION_JSON));

        Tweet tweet = twitter.promotedTweetOperations().createPromotedOnlyTweet(
                mockedAccountId,
                new PromotedOnlyTweetFormBuilder());

        assertTweetContents(tweet);
    }

    private void assertTweetContents(Tweet tweet) {
        Assert.assertNotNull(tweet);

        Assert.assertEquals(243145735212777472L, tweet.getId());
        Assert.assertEquals("Maybe he'll finally find his keys. #peterfalk", tweet.getText());
        Assert.assertEquals("jasoncosta", tweet.getFromUser());
        Assert.assertNull(tweet.getInReplyToScreenName());

        //Assert.assertNotNull(tweet.getLanguageCode()); -> not required

        Assert.assertEquals("http://a0.twimg.com/profile_images/1751674923/new_york_beard_normal.jpg", tweet.getProfileImageUrl());
        Assert.assertEquals("<a>My Shiny App</a>", tweet.getSource());
        Assert.assertEquals("Maybe he'll finally find his keys. #peterfalk", tweet.getUnmodifiedText());
        Assert.assertEquals("Tue Sep 04 17:37:15 PDT 2012", tweet.getCreatedAt().toString());

        List<String> hashtags = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        tweet.getEntities().getHashTags().stream().forEach(i -> {
            hashtags.add(i.getText());
            for (int index : i.getIndices())
                indices.add(index);
        });
        Assert.assertThat(hashtags, CoreMatchers.hasItem("peterfalk"));
        Assert.assertThat(indices, CoreMatchers.hasItems(35, 45));

        Assert.assertNotNull(tweet.getEntities().getMedia());
        Assert.assertNotNull(tweet.getEntities().getMentions());
        Assert.assertNotNull(tweet.getEntities().getTickerSymbols());

        Assert.assertEquals(0, tweet.getEntities().getUrls().size());

        //Assert.assertNotNull(tweet.getFavoriteCount()); -> not required
        Assert.assertNotNull(tweet.getFromUserId());

        //Assert.assertNotNull(tweet.getInReplyToStatusId()); -> not required
        //Assert.assertNotNull(tweet.getInReplyToUserId()); -> not required

        Assert.assertNotNull(tweet.getRetweetCount());
        //Assert.assertNotNull(tweet.getRetweetedStatus()); -> not required

        Assert.assertNotNull(tweet.getToUserId());
        Assert.assertNotNull(tweet.getUser());

        Assert.assertEquals(false, tweet.isRetweet());
        Assert.assertEquals(false, tweet.isFavorited());
        Assert.assertEquals(false, tweet.isRetweeted());
    }
}
