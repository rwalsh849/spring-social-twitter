package org.springframework.social.twitter.api.impl.advertising;

import java.time.LocalDateTime;

import org.springframework.social.twitter.api.advertising.TailoredAudience;
import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.social.twitter.api.advertising.TailoredAudienceType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to {@link TailoredAudience}.
 * 
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TailoredAudienceMixin {

    @JsonCreator
    TailoredAudienceMixin(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("list_type") TailoredAudienceListType listType,
            @JsonProperty("audience_type") TailoredAudienceType audienceType,
            @JsonProperty("audience_size") Integer audienceSize,
            @JsonProperty("partner_source") String partnerSource,
            @JsonProperty("deleted") Boolean deleted,
            @JsonProperty("targetable") Boolean targetable,
            @JsonProperty("reasons_not_targetable") String[] reasonsNotTargetable,
            @JsonProperty("targetable_types") String[] targetableTypes,
            @JsonProperty("created_at") LocalDateTime createdAt,
            @JsonProperty("updated_at") LocalDateTime updatedAt) {}

}
