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

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.advertising.Card;
import org.springframework.social.twitter.api.advertising.CardQuery;
import org.springframework.social.twitter.api.advertising.CardSorting;
import org.springframework.util.MultiValueMap;

/**
 * Facilitates the creation of the query that will be
 * used to filter results from the {@link Card} endpoints.
 * 
 * @author Richard Walsh
 */
public class CardQueryBuilder
        extends AbstractTwitterQueryForSortableEntityBuilder<CardQuery, CardSorting>
        implements CardQuery {
    
    private List<String> cardIds;
    private Integer count;
    private Integer cursor;

    @Override
    protected void makeParameters(MultiValueMap<String, String> map) {
        appendParameter(map, "card_ids", this.cardIds);
        appendParameter(map, "count", this.count);
        appendParameter(map, "cursor", this.cursor);
    }

    @Override
    public CardQuery withCards(String... cardIds) {
        this.cardIds = new ArrayList<String>();
        for (int i = 0; i < cardIds.length; i++) {
            this.cardIds.add(cardIds[i]);
        }
        return this;
    }

    @Override
    public CardQuery withCount(Integer count) {
        this.count = count;
        return this;
    }
    
    @Override
    public CardQuery withCursor(Integer cursor) {
        this.cursor = cursor;
        return this;
    }
}
