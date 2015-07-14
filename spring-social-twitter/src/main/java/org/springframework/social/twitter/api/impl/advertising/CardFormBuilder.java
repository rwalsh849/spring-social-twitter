/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl.advertising;

import org.springframework.social.twitter.api.advertising.Card;
import org.springframework.social.twitter.api.advertising.CardAppCallToAction;
import org.springframework.social.twitter.api.advertising.CardForm;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Facilitate the creation of the request body for post and put
 * requests made to the management of {@link Card} data.
 * 
 * @author Richard Walsh
 */
public class CardFormBuilder extends AbstractTwitterFormBuilder implements CardForm {
    private String name;
    private String appCountryCode;
    private String iPhoneAppId;
    private String googlePlayAppId;
    private String iPadAppId;
    private CardAppCallToAction cardAppCallToAction;
    private String iPhoneDeepLink;
    private String iPadDeepLink;
    private String googlePlayDeepLink;
    private String customIconMediaId;
    private String customAppDescription;

    public CardFormBuilder() {
        
    }

    @Override
    public MultiValueMap<String, String> toRequestBody() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        appendParameter(params, "name", this.name);
        appendParameter(params, "app_country_code", this.appCountryCode);
        
        appendParameter(params, "iphone_app_id", this.iPhoneAppId);
        appendParameter(params, "ipad_app_id", this.iPadAppId);
        appendParameter(params, "googleplay_app_id", this.googlePlayAppId);
        appendParameter(params, "app_cta", this.cardAppCallToAction);
        appendParameter(params, "iphone_deep_link", this.iPhoneDeepLink);
        appendParameter(params, "ipad_deep_link", this.iPadDeepLink);
        appendParameter(params, "googleplay_deep_link", this.googlePlayDeepLink);
        
        appendParameter(params, "custom_icon_media_id", this.customIconMediaId);
        appendParameter(params, "custom_app_description", this.customAppDescription);
        
        return params;
    }

    @Override
    public CardForm withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CardForm withAppCountryCode(String appCountryCode) {
        this.appCountryCode = appCountryCode;
        return this;
    }

    @Override
    public CardForm withIPhoneAppId(String iPhoneAppId) {
        this.iPhoneAppId = iPhoneAppId;
        return this;
    }

    @Override
    public CardForm withGooglePlayAppId(String googlePlayAppId) {
        this.googlePlayAppId = googlePlayAppId;
        return this;
    }

    @Override
    public CardForm withIPadAppId(String iPadAppId) {
        this.iPadAppId = iPadAppId;
        return this;
    }

    @Override
    public CardForm withCallToAction(CardAppCallToAction cardAppCallToAction) {
        this.cardAppCallToAction = cardAppCallToAction;
        return this;
    }

    @Override
    public CardForm withIPhoneDeepLink(String iPhoneDeepLink) {
        this.iPhoneDeepLink = iPhoneDeepLink;
        return this;
    }

    @Override
    public CardForm withIPadDeepLink(String iPadDeepLink) {
        this.iPadDeepLink = iPadDeepLink;
        return this;
    }

    @Override
    public CardForm withGooglePlayDeepLink(String googlePlayDeepLink) {
        this.googlePlayDeepLink = googlePlayDeepLink;
        return this;
    }

    @Override
    public CardForm withCustomIconMediaId(String customIconMediaId) {
        this.customIconMediaId = customIconMediaId;
        return this;
    }

    @Override
    public CardForm withCustomAppDescription(String customAppDescription) {
        this.customAppDescription = customAppDescription;
        return this;
    }
}
