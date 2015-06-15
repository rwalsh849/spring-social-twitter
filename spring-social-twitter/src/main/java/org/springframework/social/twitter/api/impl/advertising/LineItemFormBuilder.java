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

import java.math.BigDecimal;

import org.springframework.social.twitter.api.advertising.AdvertisingObjective;
import org.springframework.social.twitter.api.advertising.AdvertisingPlacementType;
import org.springframework.social.twitter.api.advertising.AdvertisingSentiment;
import org.springframework.social.twitter.api.advertising.LineItem;
import org.springframework.social.twitter.api.advertising.LineItemForm;
import org.springframework.social.twitter.api.advertising.LineItemOptimization;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Facilitate the creation of the request body for post and put
 * requests made to the management of {@link LineItem} data.
 * 
 * @author Hudson Mendes
 */
public class LineItemFormBuilder extends AbstractTwitterFormBuilder implements LineItemForm {
    private String campaignId;
    private AdvertisingPlacementType placementType;
    private AdvertisingObjective objective;
    private AdvertisingSentiment includeSentiment;
    private LineItemOptimization optimization;
    private BigDecimal totalBudgetAmount;
    private BigDecimal bidAmount;
    private Boolean paused;
    private Boolean deleted;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#withCampaign(java.lang.String)
     */
    @Override
    public LineItemForm withCampaign(String campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#withPlacementType(org.springframework.social.twitter.api.advertising.
     * AdvertisingPlacementType)
     */
    @Override
    public LineItemForm withPlacementType(AdvertisingPlacementType placementType) {
        this.placementType = placementType;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#withObjective(org.springframework.social.twitter.api.advertising.
     * AdvertisingObjective)
     */
    @Override
    public LineItemForm withObjective(AdvertisingObjective objective) {
        this.objective = objective;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.social.twitter.api.impl.advertising.LineItemForm#includingSentiment(org.springframework.social.twitter.api.advertising.
     * AdvertisingSentiment)
     */
    @Override
    public LineItemForm includingSentiment(AdvertisingSentiment sentiment) {
        this.includeSentiment = sentiment;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#optimizingFor(org.springframework.social.twitter.api.advertising.
     * LineItemOptimization)
     */
    @Override
    public LineItemForm optimizingFor(LineItemOptimization optimization) {
        this.optimization = optimization;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#withTotalBudget(java.lang.String)
     */
    @Override
    public LineItemForm withTotalBudget(String totalBudgetAmount) {
        this.totalBudgetAmount = new BigDecimal(totalBudgetAmount);
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#withBidAmount(java.lang.String)
     */
    @Override
    public LineItemForm withBidAmount(String bidAmount) {
        this.bidAmount = new BigDecimal(bidAmount);
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#paused()
     */
    @Override
    public LineItemForm paused() {
        this.paused = true;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#unpaused()
     */
    @Override
    public LineItemForm unpaused() {
        this.paused = false;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#deleted()
     */
    @Override
    public LineItemFormBuilder deleted() {
        this.deleted = true;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#active()
     */
    @Override
    public LineItemFormBuilder active() {
        this.deleted = false;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.social.twitter.api.impl.advertising.LineItemForm#toRequestBody()
     */
    @Override
    public MultiValueMap<String, String> toRequestBody() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        appendParameter(params, "campaign_id", this.campaignId);

        appendParameter(params, "placement_type", this.placementType);
        appendParameter(params, "objective", this.objective);
        appendParameter(params, "include_sentiment", this.includeSentiment);
        appendParameter(params, "optimization", this.optimization);

        appendParameter(params, "total_budget_amount_local_micro", translateBigDecimalIntoMicro(this.totalBudgetAmount));
        appendParameter(params, "bid_amount_local_micro", translateBigDecimalIntoMicro(this.bidAmount));

        appendParameter(params, "paused", this.paused);
        appendParameter(params, "deleted", this.deleted);

        return params;
    }
}
