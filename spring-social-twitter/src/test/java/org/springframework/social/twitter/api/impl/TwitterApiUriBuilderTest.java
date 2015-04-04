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
	public void build_standardHost() {
		String anyResource = "any.json";
		URI standardResult = new TwitterApiUriBuilder().withResource(anyResource).build();
		assertThat(standardResult.getHost(), equalTo("api.twitter.com"));
	}
	
	@Test
	public void build_specificHosts() {
		String anyResource = "any.json";
		URI result1 = new TwitterApiUriBuilder().forStandardApi().withResource(anyResource).build();
		URI result2 = new TwitterApiUriBuilder().forAdCampaignsApi().withResource(anyResource).build();
		assertThat(result1.getHost(), not(equalTo(result2.getHost())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void build_fails_missingResource() {
		new TwitterApiUriBuilder().build();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void build_fails_invalidResource() {
		new TwitterApiUriBuilder().withResource((String) null).build();
	}
	
	@Test
	public void build_resourceOnly() {
		String resource1 = "standard_api_resource_1.js";
		URI result1 = new TwitterApiUriBuilder().forStandardApi().withResource(resource1).build();
		
		String resource2 = "standard_api_resource_2.js";
		URI result2 = new TwitterApiUriBuilder().forStandardApi().withResource(resource2).build();
		
		assertThat(versionFreePath(result1.getPath()), equalTo(resource1));
		assertThat(versionFreePath(result2.getPath()), equalTo(resource2));
		assertThat(result1.getPath(), not(equalTo(result2.getPath())));
	}
	
	@Test
	public void build_oneArgument() {
		String anyResource = "standard_api_resource.js";
		String argName = "argument_name";
		String argValue = "argument_value";
		URI result1 = new TwitterApiUriBuilder()
				.withResource(anyResource)
				.withArgument(argName, argValue)
				.build();
		
		assertThat(result1, not(nullValue()));
		assertThat(versionFreePath(result1.getPath()), equalTo(anyResource));
		assertThat(result1.getQuery(), equalTo(argName + "=" + argValue));
	}
	
	@Test
	public void build_multipleArguments_chained() {
		String anyResource = "standard_api_resource.js";
		String argName = "argument_name";
		String argValue = "argument_value";
		URI result1 = new TwitterApiUriBuilder()
				.withResource(anyResource)
				.withArgument(argName + "_1", argValue + "_1")
				.withArgument(argName + "_2", argValue + "_2")
				.withArgument(argName + "_3", argValue + "_3")
				.build();
		
		String chainedQuery =
				argName + "_1" + "=" + argValue + "_1" + "&" +
				argName + "_2" + "=" + argValue + "_2" + "&" +
				argName + "_3" + "=" + argValue + "_3";
		
		assertThat(result1, not(nullValue()));
		assertThat(versionFreePath(result1.getPath()), equalTo(anyResource));
		assertThat(result1.getQuery(), equalTo(chainedQuery));
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
