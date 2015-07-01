package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForLocationsQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForLocationsQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForLocationsQuery>
        implements TargetingCriteriaDiscoveryForLocationsQuery {

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}
}
