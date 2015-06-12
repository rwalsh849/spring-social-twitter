package org.springframework.social.twitter.api.impl;

import org.springframework.social.twitter.api.TwitterForm;
import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;

public interface TailoredAudienceForm extends TwitterForm {

    public TailoredAudienceForm withAccount(String account);

    public TailoredAudienceForm ofListType(TailoredAudienceListType listType);

    public TailoredAudienceForm named(String name);
}
