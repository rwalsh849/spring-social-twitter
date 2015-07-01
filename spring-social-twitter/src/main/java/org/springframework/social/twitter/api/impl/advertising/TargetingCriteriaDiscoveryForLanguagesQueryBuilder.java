package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForLanguagesQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForLanguagesQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForLanguagesQuery>
        implements TargetingCriteriaDiscoveryForLanguagesQuery {
	private String query;

	@Override
	public TargetingCriteriaDiscoveryForLanguagesQuery query(String query) {
		this.query = query;
		return this;
	}
	
    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
        appendParameter(map, "q", this.query);
    }

}
