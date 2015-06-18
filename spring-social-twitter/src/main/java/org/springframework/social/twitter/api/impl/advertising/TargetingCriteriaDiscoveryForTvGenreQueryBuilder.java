package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvGenreQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForTvGenreQueryBuilder
        extends AbstractTwitterQueryForDiscoveryBuilder<TargetingCriteriaDiscoveryForTvGenreQuery>
        implements TargetingCriteriaDiscoveryForTvGenreQuery {

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}
}
