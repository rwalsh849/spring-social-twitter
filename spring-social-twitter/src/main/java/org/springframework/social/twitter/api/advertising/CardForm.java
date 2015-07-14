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
package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterForm;

/**
 * Describes the contract for the builder of {@link Card}'s
 * data that will be posted / patched to the endpoint.
 * 
 * @author Richard Walsh
 */
public interface CardForm extends TwitterForm {

    /**
     * The name of the {@link Card}.
     * 
     * @param name is the name of the {@link Card}.
     * @return the fluent builder
     */
    public CardForm withName(String name);
    
    /**
     * The 2 letter ISO code for the country where the App is sold.
     * 
     * @param The 2 letter ISO code for the country where the App is sold.
     * @return the fluent builder
     */
    public CardForm withAppCountryCode(String appCountryCode);
    
    /**
     * The iPhone App id.
     * 
     * @param The iPhone App id.
     * @return the fluent builder
     */
    public CardForm withIPhoneAppId(String iPhoneAppId);
    
    /**
     * The Google Play App id.
     * 
     * @param The Google Play App id.
     * @return the fluent builder
     */
    public CardForm withGooglePlayAppId(String googlePlayAppId);
    
    /**
     * The iPad App id.
     * 
     * @param The iPad App id.
     * @return the fluent builder
     */
    public CardForm withIPadAppId(String iPadAppId);
    
    /**
     * The Call-to-Action (CTA) text for the card button. 
     * 
     * @param The Call-to-Action (CTA) text for the card button. 
     * @return the fluent builder
     */
    public CardForm withCallToAction(CardAppCallToAction cardAppCallToAction);
    
    /**
     * The iPhone deep link.
     * 
     * @param The iPhone deep link.
     * @return the fluent builder
     */
    public CardForm withIPhoneDeepLink(String iPhoneDeepLink);
    
    /**
     * The iPad deep link.
     * 
     * @param The iPad deep link.
     * @return the fluent builder
     */
    public CardForm withIPadDeepLink(String iPadDeepLink);
    
    /**
     * The Google Play deep link.
     * 
     * @param The Google Play deep link.
     * @return the fluent builder
     */
    public CardForm withGooglePlayDeepLink(String googlePlayDeepLink);
    
    /**
     * The custom icon media id.
     * 
     * @param The custom icon media id.
     * @return the fluent builder
     */
    public CardForm withCustomIconMediaId(String customIconMediaId);
    
    /**
     * The custom app description.
     * 
     * @param The custom app description.
     * @return the fluent builder
     */
    public CardForm withCustomAppDescription(String customAppDescription);
    
}
