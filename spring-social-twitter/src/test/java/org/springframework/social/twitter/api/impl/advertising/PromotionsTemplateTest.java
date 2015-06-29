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
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.advertising.ApprovalStatus;
import org.springframework.social.twitter.api.advertising.PromotableUser;
import org.springframework.social.twitter.api.advertising.PromotedTweetReference;
import org.springframework.social.twitter.api.impl.AbstractTwitterApiTest;

/**
 * @author Hudson Mendes
 */
public class PromotionsTemplateTest extends AbstractTwitterApiTest {

    @Test
    public void getPromotableUsers() {
        String mockedAccountId = "0ga0yn";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/promotable_users?with_deleted=true"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-promotable-users"), APPLICATION_JSON));

        List<PromotableUser> promotables = twitter.promotionOperations().getPromotableUsers(
                mockedAccountId,
                new PromotableUserQueryBuilder().includeDeleted(true)).getList();

        Assert.assertNotEquals(0, promotables.size());
        Assert.assertEquals("13phg", promotables.get(0).getId());
        Assert.assertEquals("gq0vqj", promotables.get(0).getAccountId());
        Assert.assertEquals(new Long("390472547"), promotables.get(0).getUserId());
        Assert.assertEquals("FULL", promotables.get(0).getPromotableUserType());
        Assert.assertEquals(false, promotables.get(0).isDeleted());
        Assert.assertEquals("2015-04-13T20:42:39", promotables.get(0).getCreatedAt().toString());
        Assert.assertEquals("2015-04-13T20:42:39", promotables.get(0).getUpdatedAt().toString());
    }

    @Test
    public void getSponsoredTweets() {
        String mockedAccountId = "0ga0yn";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId
                        + "/scoped_timeline?scoped_to=none&user_ids=390472547&trim_user=true&count=1"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-sponsored-tweets"), APPLICATION_JSON));

        List<Tweet> tweets = twitter.promotionOperations().getSponsoredTweets(
                mockedAccountId,
                new SponsoredTweetQueryBuilder()
                        .ofUsers(new Long("390472547"))
                        .pagedBy(null, 1)
                        .trimUser(true)).getList();

        assertTweetsContents(tweets);
    }

    private void assertTweetsContents(List<Tweet> tweets) {
        Assert.assertEquals(3, tweets.size());

        Assert.assertEquals(new Long("540565364179226624").longValue(), tweets.get(0).getId());
        Assert.assertEquals(new Long("390472547").longValue(), tweets.get(0).getFromUserId());
        Assert.assertEquals("en", tweets.get(0).getLanguageCode());
        Assert.assertEquals("MemeTV wants to bring meme-worthy TV clips to Tumblr and... http://t.co/cfxJOQ9zpF", tweets.get(0).getText());
        Assert.assertEquals("<a href=\"http://ifttt.com\" rel=\"nofollow\">IFTTT</a>", tweets.get(0).getSource());
        Assert.assertEquals(false, tweets.get(0).isFavorited());
        Assert.assertEquals(Integer.valueOf(0), tweets.get(0).getFavoriteCount());
        Assert.assertEquals(false, tweets.get(0).isRetweeted());
        Assert.assertEquals(Integer.valueOf(0), tweets.get(0).getRetweetCount());
        Assert.assertEquals(false, tweets.get(0).isTruncated());
        Assert.assertEquals(false, tweets.get(0).isPossiblySensitive());
        Assert.assertEquals(0, tweets.get(0).getEntities().getHashTags().size());
        Assert.assertEquals(0, tweets.get(0).getEntities().getTickerSymbols().size());
        Assert.assertEquals(1, tweets.get(0).getEntities().getUrls().size());
        Assert.assertEquals("http://t.co/cfxJOQ9zpF", tweets.get(0).getEntities().getUrls().get(0).getUrl());
        Assert.assertEquals("http://ift.tt/1tLavU9", tweets.get(0).getEntities().getUrls().get(0).getExpandedUrl());
        Assert.assertEquals("ift.tt/1tLavU9", tweets.get(0).getEntities().getUrls().get(0).getDisplayUrl());
        Assert.assertEquals("Thu Dec 04 09:56:40 PST 2014", tweets.get(0).getCreatedAt().toString());
        Assert.assertThat(
                Arrays.asList(Arrays.stream(tweets.get(0).getEntities().getUrls().get(0).getIndices()).mapToObj(i -> Integer.valueOf(i))
                        .toArray(size -> new Integer[size])),
                CoreMatchers.hasItems(Integer.valueOf(60), Integer.valueOf(82)));
    }

    @Test
    public void createSponsoredTweet() {
        String mockedAccountId = "hkk5";
        String mockedTweetText = "Hey, here is a sponsored tweet that we are creating via #ads API.";
        long mockedUserId = 390472547L;

        String chainedPostContent = "status=Hey%2C+here+is+a+sponsored+tweet+that+we+are+creating+via+%23ads+API.&as_user_id=390472547";

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/tweet"))
                .andExpect(method(POST))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-sponsored-tweets-single"), APPLICATION_JSON));

        Tweet tweet = twitter.promotionOperations().createSponsoredTweet(
                mockedAccountId,
                new SponsoredTweetFormBuilder()
                        .asUser(mockedUserId)
                        .withStatus(mockedTweetText));

        Assert.assertEquals(mockedUserId, tweet.getFromUserId());
        Assert.assertEquals(mockedTweetText, tweet.getText());
        Assert.assertThat(
                Arrays.asList(tweet.getEntities().getHashTags().stream().map(i -> i.getText()).toArray(size -> new String[size])),
                CoreMatchers.hasItems("ads"));
    }

    @Test
    public void getPromotedTweetReferences() {
        String mockedAccountId = "0ga0yn";
        String mockedLineItemId = "u4h4";
        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/promoted_tweets?line_item_id=" + mockedLineItemId))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ad-promoted-tweet-reference"), APPLICATION_JSON));

        List<PromotedTweetReference> references = twitter.promotionOperations().getPromotedTweetReferences(
                mockedAccountId,
                mockedLineItemId,
                new PromotedTweetReferenceQueryBuilder()).getList();

        Assert.assertNotEquals(0, references.size());
        Assert.assertEquals("tifo", references.get(0).getId());
        Assert.assertEquals("b51j", references.get(0).getLineItemId());
        Assert.assertEquals(Long.valueOf(614564626060062720L), references.get(0).getTweetId());
        Assert.assertEquals(ApprovalStatus.ACCEPTED, references.get(0).getApprovalStatus());
        Assert.assertEquals("2015-06-27T00:21:53", references.get(0).getCreatedAt().toString());
        Assert.assertEquals("2015-06-30T00:21:53", references.get(0).getUpdatedAt().toString());
    }

    @Test
    public void createPromotedTweetReference() {
        String mockedAccountId = "0ga0yn";
        String mockedLineItemId = "u4h4";
        Long tweetId = 614564626060062720L;

        String chainedPostContent = "line_item_id=" + mockedLineItemId + "&tweet_ids=" + tweetId;

        mockServer
                .expect(requestTo("https://ads-api.twitter.com/0/accounts/" + mockedAccountId + "/promoted_tweets"))
                .andExpect(method(POST))
                .andExpect(content().string(chainedPostContent))
                .andRespond(withSuccess(jsonResource("ad-promoted-tweet-reference-creation"), APPLICATION_JSON));

        List<PromotedTweetReference> references = twitter.promotionOperations().createPromotedTweetReference(
                mockedAccountId,
                new PromotedTweetReferenceFormBuilder()
                        .onLineItemId(mockedLineItemId)
                        .forTweets(tweetId)).getList();

        Assert.assertNotEquals(0, references.size());
        Assert.assertEquals("tifo", references.get(0).getId());
        Assert.assertEquals("u4h4", references.get(0).getLineItemId());
        Assert.assertEquals(Long.valueOf(614564626060062720L), references.get(0).getTweetId());
        Assert.assertEquals(ApprovalStatus.ACCEPTED, references.get(0).getApprovalStatus());
        Assert.assertEquals("2015-06-27T00:21:53", references.get(0).getCreatedAt().toString());
        Assert.assertEquals("2015-06-30T00:21:53", references.get(0).getUpdatedAt().toString());
    }
}
