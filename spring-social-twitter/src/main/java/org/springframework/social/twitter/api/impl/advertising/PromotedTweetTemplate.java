package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.advertising.PromotedOnlyTweetForm;
import org.springframework.social.twitter.api.advertising.PromotedTweetOperations;
import org.springframework.social.twitter.api.impl.AbstractTwitterOperations;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForUri;
import org.springframework.social.twitter.api.impl.TwitterApiUriResourceForAdvertising;
import org.springframework.web.client.RestTemplate;

public class PromotedTweetTemplate extends AbstractTwitterOperations implements PromotedTweetOperations {
    private final RestTemplate restTemplate;

    public PromotedTweetTemplate(RestTemplate restTemplate, boolean isUserAuthorized, boolean isAppAuthorized) {
        super(isUserAuthorized, isAppAuthorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public Tweet createPromotedOnlyTweet(
            String accountId,
            PromotedOnlyTweetForm input) {

        requireUserAuthorization();
        return restTemplate.postForObject(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.PROMOTED_TWEETS)
                        .withArgument("account_id", accountId)
                        .build(),
                input.toRequestBody(),
                Tweet.class);
    }
}
