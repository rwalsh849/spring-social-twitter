/*
 * Copyright 2014 the original author or authors. Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.springframework.social.twitter.api.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.social.support.URIBuilder;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Builds API URIs and provide them to operations implementing {@link AbstractTwitterOperations} for both the Standard API and for the
 * Twitter AdCampaign API.
 * 
 * @author Hudson Mendes
 */
public class TwitterApiUriBuilder {

    private final MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
    private String resource;
    private String baseLocation = treatBaseUrl(TwitterApiHosts.getStandardApi());

    public TwitterApiUriBuilder forStandardApi() {
        this.baseLocation = treatBaseUrl(TwitterApiHosts.getStandardApi());
        return this;
    }

    public TwitterApiUriBuilder forAdCampaignsApi() {
        this.baseLocation = treatBaseUrl(TwitterApiHosts.getAdsApi());
        return this;
    }

    public TwitterApiUriBuilder withResource(String resource) {
        this.resource = resource;
        return this;
    }

    public TwitterApiUriBuilder withResource(TwitterApiUriResourceForAdvertising resource) {
        this.resource = resource.getPath();
        return this.forAdCampaignsApi();
    }

    public TwitterApiUriBuilder withResource(TwitterApiUriResourceForStandard resource) {
        this.resource = resource.getPath();
        return this.forStandardApi();
    }

    public TwitterApiUriBuilder withArgument(String argument, Object value) {
        this.parameters.add(argument, value.toString());
        return this;
    }

    public TwitterApiUriBuilder withArgument(MultiValueMap<String, Object> arguments) {
        this.parameters.putAll(arguments);
        return this;
    }

    public URI build() {
        this.assertRequirements();
        URI output = URIBuilder
                .fromUri(makeFullyQualifiedResourcePath())
                .queryParams(makeCompatbileQueryParameters())
                .build();

        return output;
    }

    private void assertRequirements() {
        Assert.notNull(this.resource, "You have to provide a 'resource' in order to build the Uri.");
    }

    private String makeFullyQualifiedResourcePath() {
        String qualifiedPath = qualifyPath();
        return replaceImplicitArguments(qualifiedPath);
    }

    private MultiValueMap<String, String> makeCompatbileQueryParameters() {
        MultiValueMap<String, String> output = new LinkedMultiValueMap<String, String>();
        for (Iterator<String> i = this.parameters.keySet().iterator(); i.hasNext();) {
            String key = i.next();
            this.parameters.get(key).forEach(value -> {
                output.add(key, value.toString());
            });
        }
        return output;
    }

    private String replaceImplicitArguments(String path) {

        String finalPath = path;
        List<String> toRemove = new ArrayList<String>();

        for (Iterator<String> i = this.parameters.keySet().iterator(); i.hasNext();) {
            String key = i.next();
            String argName = (":" + key).toLowerCase();

            if (path.toLowerCase().contains(argName)) {
                List<Object> values = this.parameters.get(key);
                for (int j = 0; j < values.size(); j++) {
                    Object value = values.get(j);
                    finalPath = finalPath.replace(argName, value.toString());
                }
                toRemove.add(key);
            }
        }

        toRemove.forEach(key -> this.parameters.remove(key));
        return finalPath;
    }

    private String qualifyPath() {
        String qualified = trimUriPart(this.baseLocation) + "/" + trimUriPart(this.resource);
        return qualified;
    }

    private String trimUriPart(String part) {
        return part.replaceAll("[/]+$", "");
    }

    private String treatBaseUrl(String apiHost) {
        if (apiHost == null)
            throw new IllegalArgumentException("The 'apiHost' is null and cannot be treated");

        apiHost = apiHost.trim();
        if (apiHost.isEmpty())
            throw new IllegalArgumentException("The 'apiHost' is null and cannot be treated");
        if (!apiHost.startsWith("https://"))
            throw new IllegalArgumentException("The 'apiHost' must start with 'https://'");

        if (!apiHost.endsWith("/")) {
            apiHost += "/";
        }

        return apiHost;
    }
}
