package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForEntity;

public interface TargetingCriteriaQuery extends TwitterQueryForEntity<TargetingCriteriaQuery, TargetingCriteriaSorting> {
	public TargetingCriteriaQuery withLineItem(String lineItemId);
}
