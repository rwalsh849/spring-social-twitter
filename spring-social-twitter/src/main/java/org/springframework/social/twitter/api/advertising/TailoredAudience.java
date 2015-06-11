package org.springframework.social.twitter.api.advertising;

import java.time.LocalDateTime;

import org.springframework.social.twitter.api.TwitterObject;

/**
 * Represents a Tailored Audience
 * 
 * Source: https://support.twitter.com/articles/20172017-tailored-audiences
 * Tailored audiences are a way to target your existing users and customers
 * to create highly relevant campaigns.
 * 
 * @author Hudson Mendes
 */
public class TailoredAudience extends TwitterObject {
    private final String id;
    private final String name;
    private final TailoredAudienceType audienceType;
    private final TailoredAudienceListType listType;
    private final Integer audienceSize;
    private final String partnerSource;
    private final Boolean deleted;
    private final Boolean targetable;
    private final String[] reasonsNotTargetable;
    private final String[] targetableTypes;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TailoredAudience(
            String id,
            String name,
            TailoredAudienceListType listType,
            TailoredAudienceType audienceType,
            Integer audienceSize,
            String partnerSource,
            Boolean deleted,
            Boolean targetable,
            String[] reasonsNotTargetable,
            String[] targetableTypes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;

        this.audienceType = audienceType;
        this.listType = listType;

        this.audienceSize = audienceSize;
        this.partnerSource = partnerSource;

        this.deleted = deleted;
        this.targetable = targetable;

        this.reasonsNotTargetable = reasonsNotTargetable;
        this.targetableTypes = targetableTypes;

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TailoredAudienceType getAudienceType() {
        return audienceType;
    }

    public TailoredAudienceListType getListType() {
        return listType;
    }

    public Integer getAudienceSize() {
        return audienceSize;
    }

    public String getPartnerSource() {
        return partnerSource;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Boolean isTargetable() {
        return targetable;
    }

    public String[] getReasonsNotTargetable() {
        return reasonsNotTargetable;
    }

    public String[] getTargetableTypes() {
        return targetableTypes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
