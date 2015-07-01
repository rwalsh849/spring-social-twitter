package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForPlatformsQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForPlatformsQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForPlatformsQuery>
        implements TargetingCriteriaDiscoveryForPlatformsQuery {
	private String query;

	@Override
	public TargetingCriteriaDiscoveryForPlatformsQuery query(String query) {
		this.query = query;
		return this;
	}

	@Override
    protected void makeParameters(MultiValueMap<String, String> map) {
    	appendParameter(map, "q", this.query);
    }

}
