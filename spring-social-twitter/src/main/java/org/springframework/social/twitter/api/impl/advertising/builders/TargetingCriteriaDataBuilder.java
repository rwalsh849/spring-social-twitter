package org.springframework.social.twitter.api.impl.advertising.builders;

import org.springframework.social.twitter.api.domain.models.advertising.TargetingType;
import org.springframework.social.twitter.api.impl.common.builders.TransferingDataBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDataBuilder extends TransferingDataBuilder {
	private String lineItemId;
	private String name;
	private TargetingType targetingType;
	private String targetingValue;
	private Boolean deleted;
	
	public TargetingCriteriaDataBuilder withLineItem(String lineItemId) {
		this.lineItemId = lineItemId;
		return this;
	}
	
	public TargetingCriteriaDataBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public TargetingCriteriaDataBuilder targeting(TargetingType targetingType, String targetingValue) {
		this.targetingType = targetingType;
		this.targetingValue = targetingValue;
		return this;
	}
	
	public TargetingCriteriaDataBuilder active() {
		this.deleted = false;
		return this;
	}
	
	public TargetingCriteriaDataBuilder deleted() {
		this.deleted = true;
		return this;
	}

	@Override
	public MultiValueMap<String, Object> toRequestParameters() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		appendParameter(params, "line_item_id", this.lineItemId);
		appendParameter(params, "name", this.name);
		appendParameter(params, "targeting_type", this.targetingType);
		appendParameter(params, "targeting_value", this.targetingValue);
		appendParameter(params, "deleted", this.deleted);
				
		return params;
	}
	
}
