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
package org.springframework.social.twitter.api.impl.common.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Utility methods for creating paging parameters for Twitter requests supporting paging.
 * @author Craig Walls
 */
public class PagingUtils {
	
	public static MultiValueMap<String, Object> buildPagingParametersWithCount(int page, int pageSize, long sinceId, long maxId) {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set("page", String.valueOf(page));
		parameters.set("count", String.valueOf(pageSize));
		if (sinceId > 0) {
			parameters.set("since_id", String.valueOf(sinceId));
		}
		if (maxId > 0) {
			parameters.set("max_id", String.valueOf(maxId));
		}
		return parameters;
	}

	public static MultiValueMap<String, Object> buildPagingParametersWithCount(int pageSize, long sinceId, long maxId) {
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set("count", String.valueOf(pageSize));
		if (sinceId > 0) {
			parameters.set("since_id", String.valueOf(sinceId));
		}
		if (maxId > 0) {
			parameters.set("max_id", String.valueOf(maxId));
		}
		return parameters;
	}

}
