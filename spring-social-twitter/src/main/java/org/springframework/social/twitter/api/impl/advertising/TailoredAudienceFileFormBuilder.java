package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.TailoredAudienceFileForm;
import org.springframework.social.twitter.api.advertising.TailoredAudienceFileOperation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TailoredAudienceFileFormBuilder extends AbstractTwitterFormBuilder implements TailoredAudienceFileForm {

    private String tailoredAudienceId;
    private String inputFilePath;
    private TailoredAudienceFileOperation operation;

    @Override
    public TailoredAudienceFileForm withTailoredAudience(String tailoredAudienceId) {
        this.tailoredAudienceId = tailoredAudienceId;
        return this;
    }

    @Override
    public TailoredAudienceFileForm withInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        return this;
    }

    @Override
    public TailoredAudienceFileForm withOperation(TailoredAudienceFileOperation operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public MultiValueMap<String, String> toRequestBody() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        appendParameter(params, "tailored_audience_id", this.tailoredAudienceId);
        appendParameter(params, "input_file_path", this.inputFilePath);
        appendParameter(params, "operation", this.operation);

        return params;
    }

}
