package org.springframework.social.twitter.api.impl.advertising;

import java.util.Locale;

import org.springframework.social.twitter.api.advertising.TargetingCriteriaDiscoveryForTvShowQuery;
import org.springframework.util.MultiValueMap;

public class TargetingCriteriaDiscoveryForTvShowQueryBuilder
        extends AbstractTwitterQueryForDiscoveryBuilder<TargetingCriteriaDiscoveryForTvShowQuery>
        implements TargetingCriteriaDiscoveryForTvShowQuery {

    private Locale tvMarketLocale;

    @Override
    public TargetingCriteriaDiscoveryForTvShowQuery withLocale(Locale tvMarketLocale) {
        this.tvMarketLocale = tvMarketLocale;
        return this;
    }

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
        appendParameter(map, "tv_market_locale", this.tvMarketLocale.toString());
    }

}
