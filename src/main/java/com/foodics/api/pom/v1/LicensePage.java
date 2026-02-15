package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * License Page Object Model - V1
 * Encapsulates all license-related API endpoints
 */
public class LicensePage extends ApiClient {

    private static final String LICENSES_FOODICS_ENDPOINT = "/licenses/foodics";
    private static final String LICENSE_BY_ID_ENDPOINT = "/licenses/foodics/{id}";

    public Response createLicense(Object licenseData, String apiKey) {
        Map<String, String> headers = Map.of("X-API-Key", apiKey);
        return withoutAuth()
                .withHeaders(headers)
                .withBody(licenseData)
                .post(LICENSES_FOODICS_ENDPOINT);
    }

    public Response getLicense(String licenseId, String apiKey) {
        Map<String, Object> pathParams = Map.of("id", licenseId);
        Map<String, String> headers = Map.of("X-API-Key", apiKey);
        return withoutAuth()
                .withHeaders(headers)
                .withPathParams(pathParams)
                .get(LICENSE_BY_ID_ENDPOINT);
    }
}
