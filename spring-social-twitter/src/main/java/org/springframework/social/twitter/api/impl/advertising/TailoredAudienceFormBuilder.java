package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.TailoredAudienceForm;
import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TailoredAudienceFormBuilder extends AbstractTwitterFormBuilder implements TailoredAudienceForm {

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

    @Override
    public TailoredAudienceFormBuilder withAccount(String accountId) {
        this.accountId = accountId;
        return this;
    }

    @Override
    public TailoredAudienceFormBuilder named(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TailoredAudienceFormBuilder ofListType(TailoredAudienceListType listType) {
        this.listType = listType;
        return this;
    }

}
