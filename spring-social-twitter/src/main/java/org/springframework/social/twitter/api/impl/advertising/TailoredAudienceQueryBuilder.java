package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TailoredAudienceQuery;
import org.springframework.util.MultiValueMap;

public class TailoredAudienceQueryBuilder extends AbstractTwitterQueryForDataBuilder<TailoredAudienceQuery> implements TailoredAudienceQuery {
    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}

}
