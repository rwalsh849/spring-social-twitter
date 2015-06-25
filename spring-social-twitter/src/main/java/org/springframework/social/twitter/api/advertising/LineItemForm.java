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
 * Describes the contract for the builder of {@link LineItem}'s
 * data that will be posted / patched to the endpoint.
 * 
 * @author Hudson Mendes
 */
public interface LineItemForm extends TwitterForm {

    /**
     * The {@link Campaign} that the {@link LineItem} is related to.
     * 
     * @param campaignId is the id of the {@link Campaign} related to the {@link LineItem}.
     * @return the fluent builder
     */
    public abstract LineItemForm withCampaign(String campaignId);

    /**
     * The type of placement for ads of a {@link LineItem}
     * 
     * @param placementType the type of placement.
     * @return the fluent builder
     */
    public abstract LineItemForm withPlacementType(AdvertisingPlacementType placementType);

    /**
     * The objective of this {@link LineItem}
     * 
     * @param objective is the objective set for this particular {@link LineItem}.
     * @return the fluent builder
     */
    public abstract LineItemForm withObjective(AdvertisingObjective objective);

    /**
     * The sentiment of the {@link LineItem} amongst the ones
     * set out in {@link AdvertisingSentiment}.
     * 
     * @param sentiment can be either POSITIVE_ONLY, ALL (check {@link AdvertisingSentiment})
     * @return the fluent builder
     */
    public abstract LineItemForm includingSentiment(AdvertisingSentiment sentiment);

    /**
     * The sentiment of the {@link LineItem} amongst the ones
     * set out in {@link LineItemOptimization}
     * 
     * @param optimization can be either DEFAULT, WEBSITE_CONVERSIONS (check {@link LineItemOptimization})
     * @return the fluent builder
     */
    public abstract LineItemForm optimizingFor(LineItemOptimization optimization);

    /**
     * The total budget for the {@link LineItem}.
     * 
     * @param totalBudgetAmount of the {@link LineItem}
     * @return the fluent builder
     */
    public abstract LineItemForm withTotalBudget(String totalBudgetAmount);

    /**
     * The bid amoung for the {@link LineItem}.
     * 
     * @param bidAmount of the {@link LineItem}
     * @return the fluent builder
     */
    public abstract LineItemForm withBidAmount(String bidAmount);

    /**
     * Defines wether the bid should be automatically selected or not
     * 
     * @param auto true means yes, it should be automatic; false says otherwise.
     * @return the fluent builder
     */
    public abstract LineItemForm automaticallySelectBid(Boolean auto);

    /**
     * Set the {@link LineItem} to paused.
     * 
     * @return the fluent builder
     */
    public abstract LineItemForm paused();

    /**
     * Unpause the {@link LineItem}.
     * 
     * @return the fluent builder
     */
    public abstract LineItemForm unpaused();

    /**
     * Set the {@link LineItem} to deleted.
     * 
     * @return the fluent builder
     */
    public abstract LineItemForm deleted();

    /**
     * Undelete the {@link LineItem}.
     * 
     * @return the fluent builder
     */
    public abstract LineItemForm active();

}
