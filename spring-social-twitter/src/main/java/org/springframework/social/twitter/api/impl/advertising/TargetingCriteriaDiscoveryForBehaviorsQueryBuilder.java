package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForBehaviorsQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForBehaviorsQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForBehaviorsQuery>
        implements TargetingCriteriaDiscoveryForBehaviorsQuery {

	private final List<String> behaviorIds = new ArrayList<>();
	private String condition;
	
	@Override
	public TargetingCriteriaDiscoveryForBehaviorsQuery ofBehaviors(String... behaviorIds) {
		if(behaviorIds!=null) {
			for(String behaviorId: behaviorIds) {
				this.behaviorIds.add(behaviorId);
			}
		}
		return this;
	}

	@Override
	public TargetingCriteriaDiscoveryForBehaviorsQuery sortBy(String field, String direction) {
		if(field!=null && direction!=null) {
			this.condition = field + "-" + direction;
		}
		return this;
	}

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
        appendParameter(map, "behavior_ids", this.behaviorIds);
        appendParameter(map, "sort_by", this.condition);
    }

}
