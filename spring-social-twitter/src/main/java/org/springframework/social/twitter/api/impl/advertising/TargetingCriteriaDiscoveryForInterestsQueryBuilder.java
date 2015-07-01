package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForInterestsQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForInterestsQueryBuilder
        extends AbstractTwitterQueryForEntityBuilder<TargetingCriteriaDiscoveryForInterestsQuery>
        implements TargetingCriteriaDiscoveryForInterestsQuery {

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}
}
