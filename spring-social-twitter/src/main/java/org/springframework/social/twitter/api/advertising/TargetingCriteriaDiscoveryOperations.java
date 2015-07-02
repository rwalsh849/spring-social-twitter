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

import org.springframework.social.twitter.api.impl.DataListHolder;

/**
 * Interface defining the operations for advertising statistical operations.
 * 
 * @author Hudson Mendes
 */
public interface TargetingCriteriaDiscoveryOperations {

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForAppStoreCategories}
     */
    DataListHolder<TargetingCriteriaDiscoveryForAppStoreCategories> appStoreCategories(TargetingCriteriaDiscoveryForAppStoreCategoriesQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForDevices}
     */
    DataListHolder<TargetingCriteriaDiscoveryForDevices> devices(TargetingCriteriaDiscoveryForDevicesQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForBehaviorTaxonomies}
     */
    DataListHolder<TargetingCriteriaDiscoveryForBehaviorTaxonomies> behaviorTaxonomies(TargetingCriteriaDiscoveryForBehaviorTaxonomiesQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForBehaviors}
     */
    DataListHolder<TargetingCriteriaDiscoveryForBehaviors> behaviors(TargetingCriteriaDiscoveryForBehaviorsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForInterests}
     */
    DataListHolder<TargetingCriteriaDiscoveryForInterests> interests(TargetingCriteriaDiscoveryForInterestsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForLanguages}
     */
    DataListHolder<TargetingCriteriaDiscoveryForLanguages> languages(TargetingCriteriaDiscoveryForLanguagesQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForLocations}
     */
    DataListHolder<TargetingCriteriaDiscoveryForLocations> locations(TargetingCriteriaDiscoveryForLocationsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForNetworkOperators}
     */
    DataListHolder<TargetingCriteriaDiscoveryForNetworkOperators> networkOperators(TargetingCriteriaDiscoveryForNetworkOperatorsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForPlatformVersions}
     */
    DataListHolder<TargetingCriteriaDiscoveryForPlatformVersions> platformVersions(TargetingCriteriaDiscoveryForPlatformVersionsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForPlatforms}
     */
    DataListHolder<TargetingCriteriaDiscoveryForPlatforms> platforms(TargetingCriteriaDiscoveryForPlatformsQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForTvShow}
     */
    DataListHolder<TargetingCriteriaDiscoveryForTvShow> tvShow(TargetingCriteriaDiscoveryForTvShowQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForTvMarket}
     */
    DataListHolder<TargetingCriteriaDiscoveryForTvMarket> tvMarkets(TargetingCriteriaDiscoveryForTvMarketQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForTvGenre}
     */
    DataListHolder<TargetingCriteriaDiscoveryForTvGenre> tvGenres(TargetingCriteriaDiscoveryForTvGenreQuery query);

    /**
     * @return an instance of {@link TargetingCriteriaDiscoveryForTvChannel}
     */
    DataListHolder<TargetingCriteriaDiscoveryForTvChannel> tvChannels(TargetingCriteriaDiscoveryForTvChannelQuery query);
}
