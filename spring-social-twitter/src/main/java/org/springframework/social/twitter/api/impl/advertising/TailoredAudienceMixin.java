package org.springframework.social.twitter.api.impl.advertising;

import java.time.LocalDateTime;

import org.springframework.social.twitter.api.advertising.TailoredAudience;
import org.springframework.social.twitter.api.advertising.TailoredAudienceListType;
import org.springframework.social.twitter.api.advertising.TailoredAudienceType;
import org.springframework.social.twitter.api.impl.LocalDateTimeDeserializer;
import org.springframework.social.twitter.api.impl.TwitterObjectMixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Mixin class for adding Jackson annotations to {@link TailoredAudience}.
 * 
 * @author Hudson Mendes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TailoredAudienceMixin extends TwitterObjectMixin {

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
            @JsonProperty("created_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime createdAt,
            @JsonProperty("updated_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime updatedAt) {}

}
