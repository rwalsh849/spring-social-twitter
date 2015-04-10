package org.springframework.social.twitter.api.impl.advertising.templates;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.twitter.api.domain.models.TransferingData;
import org.springframework.social.twitter.api.domain.models.advertising.TargetingCriteria;
import org.springframework.social.twitter.api.domain.operations.TargetingCriteriaOperations;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriBuilder;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriResourceForAdvertising;
import org.springframework.social.twitter.api.impl.common.holders.DataListHolder;
import org.springframework.social.twitter.api.impl.common.holders.DataSingleHolder;
import org.springframework.social.twitter.api.impl.common.templates.AbstractTwitterTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link TargetingCriteriaOperations}, providing a binding to Twitter's direct message-oriented REST resources.
 * @author Hudson Mendes
 */
public class TargetingCriteriaTemplate extends AbstractTwitterTemplate implements TargetingCriteriaOperations {
	private final RestTemplate restTemplate;

	public TargetingCriteriaTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<TargetingCriteria> getTargetingCriterias(String accountId) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.TARGETING_CRITERIAS)
					.withArgument("account_id", accountId)
					.withArgument("with_deleted", "true")
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataListHolder<TargetingCriteria>>(){}
			).getBody().getData();
	}

	@Override
	public TargetingCriteria getTargetingCriteria(String accountId, String id) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.TARGETING_CRITERIA)
					.withArgument("account_id", accountId)
					.withArgument("targeting_criteria_id", id)
					.build(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<DataSingleHolder<TargetingCriteria>>(){}
			).getBody().getData();
	}

	@Override
	public TargetingCriteria createTargetingCriteria(String accountId, TransferingData data) {
		requireUserAuthorization();
		return restTemplate.exchange(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.TARGETING_CRITERIAS)
					.withArgument("account_id", accountId)
					.build(),
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, Object>>(data.toRequestParameters()),
				new ParameterizedTypeReference<DataSingleHolder<TargetingCriteria>>(){}
			).getBody().getData();
	}
	
	@Override
	public void updateTargetingCriteria(String accountId, String id, TransferingData data) {
		requireUserAuthorization();
		restTemplate.put(
				new TwitterApiUriBuilder()
					.withResource(TwitterApiUriResourceForAdvertising.TARGETING_CRITERIA)
					.withArgument("account_id", accountId)
					.withArgument("targeting_criteria_id", id)
					.build(),
				data.toRequestParameters());
	}

	@Override
	public void deleteTargetingCriteria(String accountId, String id) {
		requireUserAuthorization();
		restTemplate.delete(new TwitterApiUriBuilder()
			.withResource(TwitterApiUriResourceForAdvertising.TARGETING_CRITERIA)
			.withArgument("account_id", accountId)
			.withArgument("targeting_criteria_id", id)
			.build());
	}
	
}
