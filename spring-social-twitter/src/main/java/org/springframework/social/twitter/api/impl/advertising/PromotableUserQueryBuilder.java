package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.PromotableUserQuery;
import org.springframework.util.MultiValueMap;

public class PromotableUserQueryBuilder extends AbstractTwitterQueryForEntityBuilder<PromotableUserQuery> implements PromotableUserQuery {

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {}
}
