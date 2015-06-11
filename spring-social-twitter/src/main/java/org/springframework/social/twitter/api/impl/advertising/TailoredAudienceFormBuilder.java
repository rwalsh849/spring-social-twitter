package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TailoredAudienceFormBuilder extends AbstractTwitterFormBuilder {

    private String accountId;
    private String name;
    private TailoredAudienceListType listType;

    @Override
    public MultiValueMap<String, String> toRequestBody() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        appendParameter(params, "account_id", this.accountId);
        appendParameter(params, "name", this.name);
        appendParameter(params, "list_type", this.listType);

        return params;
    }

    public TailoredAudienceFormBuilder withAccount(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public TailoredAudienceFormBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TailoredAudienceFormBuilder OfListType(TailoredAudienceListType listType) {
        this.listType = listType;
        return this;
    }

}
