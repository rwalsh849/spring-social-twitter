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
package org.springframework.social.twitter.api.impl.standard.templates;

import java.net.URI;
import java.util.List;

import org.springframework.social.twitter.api.common.operations.GeoOperations;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriBuilder;
import org.springframework.social.twitter.api.impl.common.builders.TwitterApiUriResourceForStandard;
import org.springframework.social.twitter.api.impl.common.templates.AbstractTwitterTemplate;
import org.springframework.social.twitter.api.impl.standard.holders.PlacesList;
import org.springframework.social.twitter.api.impl.standard.models.Place;
import org.springframework.social.twitter.api.impl.standard.models.PlacePrototype;
import org.springframework.social.twitter.api.impl.standard.models.PlaceType;
import org.springframework.social.twitter.api.impl.standard.models.SimilarPlaces;
import org.springframework.social.twitter.api.impl.standard.models.SimilarPlacesResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GeoTemplate extends AbstractTwitterTemplate implements GeoOperations {

	private final RestTemplate restTemplate;

	public GeoTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp) {
		super(isAuthorizedForUser, isAuthorizedForApp);
		this.restTemplate = restTemplate;
	}

	public Place getPlace(String placeId) {
		requireUserAuthorization();
		TwitterApiUriResourceForStandard resource = TwitterApiUriResourceForStandard.GEO_ID;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument("place_id", placeId).build();
		return restTemplate.getForObject(resourceUri, Place.class);
	}
	
	public List<Place> reverseGeoCode(double latitude, double longitude) {
		return reverseGeoCode(latitude, longitude, null, null);
	}
	
	public List<Place> reverseGeoCode(double latitude, double longitude, PlaceType granularity, String accuracy) {
		requireUserAuthorization();
		TwitterApiUriResourceForStandard resource = TwitterApiUriResourceForStandard.GEO_REVERSE_GEOCODE;
		MultiValueMap<String, String> parameters = buildGeoParameters(latitude, longitude, granularity, accuracy, null);
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument(parameters).build();
		return restTemplate.getForObject(resourceUri, PlacesList.class).getList();
	}
	
	public List<Place> search(double latitude, double longitude) {
		return search(latitude, longitude, null, null, null);
	}
	
	public List<Place> search(double latitude, double longitude, PlaceType granularity, String accuracy, String query) {
		requireUserAuthorization();
		TwitterApiUriResourceForStandard resource = TwitterApiUriResourceForStandard.GEO_SEARCH;
		MultiValueMap<String, String> parameters = buildGeoParameters(latitude, longitude, granularity, accuracy, query);
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument(parameters).build();
		return restTemplate.getForObject(resourceUri, PlacesList.class).getList();
	}
	
	public SimilarPlaces findSimilarPlaces(double latitude, double longitude, String name) {
		return findSimilarPlaces(latitude, longitude, name, null, null);
	}
	
	public SimilarPlaces findSimilarPlaces(double latitude, double longitude, String name, String streetAddress, String containedWithin) {
		requireUserAuthorization();
		TwitterApiUriResourceForStandard resource = TwitterApiUriResourceForStandard.GEO_SIMILAR_PLACES;
		MultiValueMap<String, String> parameters = buildPlaceParameters(latitude, longitude, name, streetAddress, containedWithin);
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).withArgument(parameters).build();
		SimilarPlacesResponse response = restTemplate.getForObject(resourceUri, SimilarPlacesResponse.class);
		PlacePrototype placePrototype = new PlacePrototype(response.getToken(), latitude, longitude, name, streetAddress, containedWithin);	
		return new SimilarPlaces(response.getPlaces(), placePrototype);
	}
	
	public Place createPlace(PlacePrototype placePrototype) {
		requireUserAuthorization();
		TwitterApiUriResourceForStandard resource = TwitterApiUriResourceForStandard.GEO_PLACE;
		URI resourceUri = new TwitterApiUriBuilder().withResource(resource).build();
		MultiValueMap<String, String> bodyData = buildPlaceParameters(placePrototype.getLatitude(), placePrototype.getLongitude(), placePrototype.getName(), placePrototype.getStreetAddress(), placePrototype.getContainedWithin());
		bodyData.set("token", placePrototype.getCreateToken());
		return restTemplate.postForObject(resourceUri, bodyData, Place.class);		
	}
	
	// private helpers
	
	private MultiValueMap<String, String> buildGeoParameters(double latitude, double longitude, PlaceType granularity, String accuracy, String query) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("lat", String.valueOf(latitude));
		parameters.set("long", String.valueOf(longitude));
		if(granularity != null) {
			parameters.set("granularity", granularity.equals(PlaceType.POINT_OF_INTEREST) ? "poi" : granularity.toString().toLowerCase());
		}		
		if(accuracy != null) {
			parameters.set("accuracy", accuracy);
		}
		if(query != null ) {
			parameters.set("query", query);
		}
		return parameters;
	}
	
	private MultiValueMap<String, String> buildPlaceParameters(double latitude, double longitude, String name, String streetAddress, String containedWithin) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("lat", String.valueOf(latitude));
		parameters.set("long", String.valueOf(longitude));
		parameters.set("name", name);
		if(streetAddress != null) {
			parameters.set("attribute:street_address", streetAddress);
		}
		if(containedWithin != null) {
			parameters.set("contained_within", containedWithin);
		}
		return parameters;
	}

}
