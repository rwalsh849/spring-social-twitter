package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GlobalOptOutFormBuilder extends AbstractTwitterFormBuilder {
    private String accountId;
    private String tonFilePath;
    private TailoredAudienceListType listType;

    @Override
    public MultiValueMap<String, String> toRequestBody() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        appendParameter(params, "account_id", this.accountId);
        appendParameter(params, "input_file_path", this.tonFilePath);
        appendParameter(params, "list_type", this.listType);
        return params;
    }

    public GlobalOptOutFormBuilder withAccount(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GlobalOptOutFormBuilder withInputFilePath(String tonFilePath) {
        this.tonFilePath = tonFilePath;
        return this;
    }

    public GlobalOptOutFormBuilder withListType(TailoredAudienceListType listType) {
        this.listType = listType;
        return this;
    }

}
