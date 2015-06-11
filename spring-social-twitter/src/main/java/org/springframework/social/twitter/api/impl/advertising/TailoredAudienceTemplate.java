package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.TailoredAudienceForm;
import org.springframework.social.twitter.api.advertising.TailoredAudience;
import org.springframework.social.twitter.api.advertising.TailoredAudienceOperations;
import org.springframework.social.twitter.api.advertising.TailoredAudienceQuery;
import org.springframework.social.twitter.api.impl.AbstractTwitterOperations;
import org.springframework.social.twitter.api.impl.DataListHolder;
import org.springframework.social.twitter.api.impl.DataSingleHolder;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForBody;
import org.springframework.social.twitter.api.impl.TwitterApiBuilderForUri;
import org.springframework.social.twitter.api.impl.TwitterApiUriResourceForAdvertising;
import org.springframework.web.client.RestTemplate;

public class TailoredAudienceTemplate extends AbstractTwitterOperations implements TailoredAudienceOperations {
    private final RestTemplate restTemplate;

    public TailoredAudienceTemplate(RestTemplate restTemplate, boolean isUserAuthorized, boolean isAppAuthorized) {
        super(isUserAuthorized, isAppAuthorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public TailoredAudience getTailoredAudience(String accountId, String id) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TAILORED_AUDIENCE)
                        .withArgument("account_id", accountId)
                        .withArgument("tailored_audience_id", id)
                        .withArgument("with_deleted", true)
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataSingleHolder<TailoredAudience>>() {}
                ).getBody().getData();
    }

    @Override
    public DataListHolder<TailoredAudience> getTailoredAudiences(String accountId, TailoredAudienceQuery query) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TAILORED_AUDIENCES)
                        .withArgument("account_id", accountId)
                        .withArgument(query.toQueryParameters())
                        .build(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DataListHolder<TailoredAudience>>() {}
                ).getBody();
    }

    @Override
    public TailoredAudience createTailoredAudience(String accountId, TailoredAudienceForm input) {
        requireUserAuthorization();
        return restTemplate.exchange(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TAILORED_AUDIENCES)
                        .withArgument("account_id", accountId)
                        .build(),
                HttpMethod.POST,
                new TwitterApiBuilderForBody<>(input.toRequestBody()).build(),
                new ParameterizedTypeReference<DataSingleHolder<TailoredAudience>>() {}
                ).getBody().getData();
    }

    @Override
    public void updateTailoredAudience(String accountId, String id, TailoredAudienceForm input) {
        requireUserAuthorization();
        restTemplate.put(
                new TwitterApiBuilderForUri()
                        .withResource(TwitterApiUriResourceForAdvertising.TAILORED_AUDIENCE)
                        .withArgument("account_id", accountId)
                        .withArgument("tailored_audience_id", id)
                        .build(),
                input.toRequestBody());
    }

    @Override
    public void deleteTailoredAudience(String accountId, String id) {
        requireUserAuthorization();
        restTemplate.delete(new TwitterApiBuilderForUri()
                .withResource(TwitterApiUriResourceForAdvertising.TAILORED_AUDIENCES)
                .withArgument("account_id", accountId)
                .withArgument("tailored_audience_id", id)
                .build());
    }

}
