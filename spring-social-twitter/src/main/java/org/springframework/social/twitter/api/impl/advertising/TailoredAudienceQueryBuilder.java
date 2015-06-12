package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TailoredAudienceQuery;
import org.springframework.util.MultiValueMap;

public class TailoredAudienceQueryBuilder extends AbstractTwitterQueryForDataBuilder<TailoredAudienceQuery> implements TailoredAudienceQuery {

    private String cursor;
    private Integer pageSize;

    @Override
    public TailoredAudienceQuery pagedBy(String cursor, Integer pageSize) {
        this.cursor = cursor;
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
        appendParameter(map, "cursor", this.cursor);
        appendParameter(map, "count", this.pageSize);
    }
}
