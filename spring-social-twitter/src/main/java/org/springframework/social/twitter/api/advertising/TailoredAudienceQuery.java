package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterQueryForData;

public interface TailoredAudienceQuery extends TwitterQueryForData<TailoredAudienceQuery> {

    public TailoredAudienceQuery pagedBy(String cursor, Integer pageSize);
}
