package org.springframework.social.twitter.api.advertising;

import org.springframework.social.twitter.api.TwitterForm;

/**
 * Describes the contract for the builder of {@link TargetingCriteria}'s
 * data that will be posted / patched to the endpoint.
 * 
 * @author Hudson Mendes
 */
public interface TargetingCriteriaForm extends TwitterForm {

    /**
     * The {@link LineItem} to which {@link TargetingCriteria} is related to.
     * 
     * @param lineItemId is the id of the {@link LineItem}
     * @return the fluent builder
     */
    public abstract TargetingCriteriaForm withLineItem(String lineItemId);

    /**
     * The name of the {@link TargetingCriteria}
     * 
     * @param name of the {@link TargetingCriteria};
     * @return the fluent builder
     */
    public abstract TargetingCriteriaForm withName(String name);

    /**
     * The targeting definition of the {@link TargetingCriteria}.
     * 
     * @param targetingType is the type of targeting chosen.
     * @param targetingValue is the value of the targeting chose.
     * @return the fluent builder
     */
    public abstract TargetingCriteriaForm targeting(String targetingType, String targetingValue);

    /**
     * The targeting definition of the {@link TargetingCriteria}.
     * 
     * @param targetingType is the type of targeting chosen; maybe any of the set out in {@link TargetingType}.
     * @param targetingValue is the value of the targeting chose.
     * @return the fluent builder
     */
    public abstract TargetingCriteriaForm targeting(TargetingType targetingType, String targetingValue);

    /**
     * Deletes the {@link TargetingCriteria}
     * 
     * @return the fluent builder
     */
    public abstract TargetingCriteriaForm active();

    /**
     * Reactivate the {@link TargetingCriteria}.
     * 
     * @return the fluent builder.
     */
    public abstract TargetingCriteriaForm deleted();

}
