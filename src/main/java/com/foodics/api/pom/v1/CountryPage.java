package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Country Page Object Model - V1
 * Encapsulates all country-related API endpoints
 */
public class CountryPage extends ApiClient {

    private static final String COUNTRIES_ENDPOINT = "/countries";

    public Response getCountries(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .get(COUNTRIES_ENDPOINT);
    }
}
