package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.AppStore;
import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForAppStoreCategoriesQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForAppStoreCategoriesQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForAppStoreCategoriesQuery>
        implements TargetingCriteriaDiscoveryForAppStoreCategoriesQuery {

	private AppStore appStore;
	private String query;
	
	@Override
	public TargetingCriteriaDiscoveryForAppStoreCategoriesQuery inAppStore(AppStore appStore) {
		this.appStore = appStore;
		return this;
	}

	@Override
	public TargetingCriteriaDiscoveryForAppStoreCategoriesQuery query(String query) {
		this.query = query;
		return this;
	}

	@Override
    protected void makeParameters(MultiValueMap<String, String> map) {
		appendParameter(map, "store", this.appStore);
		appendParameter(map, "q", this.query);
	}

}
