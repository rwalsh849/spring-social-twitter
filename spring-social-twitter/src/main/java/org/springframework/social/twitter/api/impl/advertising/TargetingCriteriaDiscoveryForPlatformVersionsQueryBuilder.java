package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForPlatformVersionsQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForPlatformVersionsQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForPlatformVersionsQuery>
        implements TargetingCriteriaDiscoveryForPlatformVersionsQuery {
	private String language;

	@Override
	public TargetingCriteriaDiscoveryForPlatformVersionsQuery withLanguage(String language) {
		this.language = language;
		return this;
	}

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
    	appendParameter(map, "lang", this.language);
    }

}
