package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingType;
import org.springframework.social.twitter.api.impl.AbstractTwitterFormBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaPostingDataBuilder extends AbstractTwitterFormBuilder {
	private String lineItemId;
	private String name;
	private TargetingType targetingType;
	private String targetingValue;
	private Boolean deleted;
	
	public TargetingCriteriaPostingDataBuilder withLineItem(String lineItemId) {
		this.lineItemId = lineItemId;
		return this;
	}
	
	public TargetingCriteriaPostingDataBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public TargetingCriteriaPostingDataBuilder targeting(TargetingType targetingType, String targetingValue) {
		this.targetingType = targetingType;
		this.targetingValue = targetingValue;
		return this;
	}
	
	public TargetingCriteriaPostingDataBuilder active() {
		this.deleted = false;
		return this;
	}
	
	public TargetingCriteriaPostingDataBuilder deleted() {
		this.deleted = true;
		return this;
	}

	@Override
	public MultiValueMap<String, Object> toRequestBody() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		appendParameter(params, "line_item_id", this.lineItemId);
		appendParameter(params, "name", this.name);
		appendParameter(params, "targeting_type", this.targetingType);
		appendParameter(params, "targeting_value", this.targetingValue);
		appendParameter(params, "deleted", this.deleted);
				
		return params;
	}
	
}
