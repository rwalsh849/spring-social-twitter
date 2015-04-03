/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.twitter.api.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.Arrays;

import org.junit.Test;

/**
 * @author Hudson Mendes
 */
public class TwitterApiUriBuilderTest {
	
	@Test
	public void build_specificHosts() {
		String anyResource = "any.json";
		URI result1 = new TwitterApiUriBuilder().forStandardApi().withResource(anyResource).build();
		URI result2 = new TwitterApiUriBuilder().forAdCampaignsApi().withResource(anyResource).build();
		assertThat(result1.getHost(), not(equalTo(result2.getHost())));
	}
	
	@Test
	public void build_standardApi_resourceOnly() {
		fail("Not implemented");
	}
	
	@Test
	public void build_standardApi_oneArgument() {
		fail("Not implemented");
	}
	
	@Test
	public void build_standardApi_multipleArguments() {
		fail("Not implemented");
	}
	
	@Test
	public void build_adCampaignsApi_resourceOnly() {
		TwitterApiUriAdCampaignResource resource1 = TwitterApiUriAdCampaignResource.ACCOUNT;
		URI result1 = new TwitterApiUriBuilder().forAdCampaignsApi().withResource(resource1).build();
		
		TwitterApiUriAdCampaignResource resource2 = TwitterApiUriAdCampaignResource.CAMPAIGN;
		URI result2 = new TwitterApiUriBuilder().forAdCampaignsApi().withResource(resource2).build();
		
		assertThat(versionFreePath(result1.getPath()), equalTo(resource1.getValue()));
		assertThat(versionFreePath(result2.getPath()), equalTo(resource2.getValue()));
		assertThat(result1.getPath(), not(equalTo(result2.getPath())));
	}
	
	@Test
	public void build_adCampaignsApi_oneArgument() {
		fail("Not implemented");
	}
	
	@Test
	public void build_adCampaignsApi_multipleArguments() {
		fail("Not implemented");
	}
	
	private String versionFreePath(String path) {
		String splitter = "/";
		StringBuilder newPath = new StringBuilder();
		Arrays.stream(path.split(splitter))
			.filter(frag -> !frag.isEmpty())
			.skip(1)
			.forEach(frag -> {
				if (newPath.length() != 0) {
					newPath.append(splitter);
				}
				
				newPath.append(frag);
			});
		
		return newPath.toString();
	}
}
