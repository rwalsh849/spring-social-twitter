package org.springframework.social.twitter.api;

import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;

public interface TailoredAudienceForm extends TwitterForm {

    public TailoredAudienceForm named(String name);

    public TailoredAudienceForm withAccount(String account);

    public TailoredAudienceForm withListType(TailoredAudienceListType listType);
}
