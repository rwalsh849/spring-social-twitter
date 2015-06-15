package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterObject;

public class TailoredAudienceFile extends TwitterObject {
    private final String id;
    private final String tailoredAudienceId;
    private final String inputFilePath;
    private final TailoredAudienceFileOperation operation;
    private final TailoredAudienceFileState state;

    public TailoredAudienceFile(
            String id,
            String tailoredAudienceId,
            String inputFilePath,
            TailoredAudienceFileOperation operation,
            TailoredAudienceFileState state) {

        this.id = id;
        this.tailoredAudienceId = tailoredAudienceId;
        this.inputFilePath = inputFilePath;
        this.operation = operation;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getTailoredAudienceId() {
        return tailoredAudienceId;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public TailoredAudienceFileOperation getOperation() {
        return operation;
    }

    public TailoredAudienceFileState getState() {
        return state;
    }
}
