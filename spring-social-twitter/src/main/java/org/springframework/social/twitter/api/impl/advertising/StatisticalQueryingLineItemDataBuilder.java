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
package org.springframework.social.twitter.api.impl.advertising;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.StatisticalSnapshot;
import org.springframework.util.MultiValueMap;

/**
 * Builder related to {@link StatisticalSnapshot} data that generates a map (key, value)
 * that can be posted into the twitter api endpoint.
 * 
 * @author Hudson Mendes
 */
public class StatisticalQueryingLineItemDataBuilder extends StatisticalQueryingBaseDataBuilder {
	private List<String> lineItemIds;
	
	public StatisticalQueryingLineItemDataBuilder withLineItems(String... lineItemIds) {
		this.lineItemIds = new ArrayList<String>();
		for (int i = 0; i < lineItemIds.length; i++)
			this.lineItemIds.add(lineItemIds[i]);
		return this;
	}

	@Override
	protected void appendSpecificParameters(MultiValueMap<String, Object> params) {
		appendParameter(params, "line_item_ids", this.lineItemIds);
	}

}
