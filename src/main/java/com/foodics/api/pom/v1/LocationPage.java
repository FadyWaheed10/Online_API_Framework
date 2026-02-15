package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Location Page Object Model - V1
 * Encapsulates all location-related API endpoints
 */
public class LocationPage extends ApiClient {

    private static final String LOCATIONS_ENDPOINT = "/locations";

    public Response listLocations() {
        return withoutAuth()
                .get(LOCATIONS_ENDPOINT);
    }

    public Response createLocation(Object locationData) {
        return withoutAuth()
                .withBody(locationData)
                .post(LOCATIONS_ENDPOINT);
    }
}
