package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvMarketQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForTvMarketQueryBuilder
        extends AbstractTwitterQueryForDiscoveryBuilder<TargetingCriteriaDiscoveryForTvMarketQuery>
        implements TargetingCriteriaDiscoveryForTvMarketQuery {

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}
}
