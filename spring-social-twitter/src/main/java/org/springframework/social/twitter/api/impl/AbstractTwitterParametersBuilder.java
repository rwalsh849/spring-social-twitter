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
package org.springframework.social.twitter.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.util.MultiValueMap;

/**
 * Provides basic functionality for parameter building related components
 * (for both QueryBuilders and FormBuilders) that shall be especialised
 * to produce contract-specific builders.
 * 
 * @author Hudson Mendes
 */
public abstract class AbstractTwitterParametersBuilder {
    private static final BigDecimal MICRO_MULTIPLIER = new BigDecimal(1000000);

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static void appendParameter(
            MultiValueMap<String, String> params,
            String name,
            Object value) {

        if (value == null)
            return;
        if (value instanceof String && ((String) value).isEmpty())
            return;
        if (value instanceof ArrayList && ((ArrayList) value).size() == 0)
            return;
        if (value instanceof ArrayList) {
            ArrayList valueAsList = (ArrayList) value;
            String[] valueArray = (String[]) valueAsList.stream().map(i -> i.toString()).toArray(size -> new String[size]);
            params.add(name, String.join(",", valueArray));
        }
        else {
            params.set(name, value.toString());
        }

    }

    protected static Long translateBigDecimalIntoMicro(BigDecimal value) {
        if (value == null)
            return new Long(0);
        return value.multiply(MICRO_MULTIPLIER).longValue();
    }
}
