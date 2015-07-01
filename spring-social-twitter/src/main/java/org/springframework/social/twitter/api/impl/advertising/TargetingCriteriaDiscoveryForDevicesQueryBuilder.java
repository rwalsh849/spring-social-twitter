package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForDevicesQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForDevicesQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForDevicesQuery>
        implements TargetingCriteriaDiscoveryForDevicesQuery {
	private String query;
	
	@Override
	public TargetingCriteriaDiscoveryForDevicesQuery query(String query) {
		this.query = query;
		return this;
	}

	@Override
    protected void makeParameters(MultiValueMap<String, String> map) {
		appendParameter(map, "q", this.query);
	}

}
