package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Application Page Object Model - V1
 * Encapsulates all application-related API endpoints
 */
public class ApplicationPage extends ApiClient {

    private static final String APPLICATIONS_ENDPOINT = "/applications";
    private static final String APPLICATION_THEME_ENDPOINT = "/applications/{applicationId}/themes";

    public Response getApplication() {
        return withoutAuth()
                .get(APPLICATIONS_ENDPOINT);
    }

    public Response updateApplicationTheme(String applicationId, Object themeData, String conceptKey) {
        Map<String, Object> pathParams = Map.of("applicationId", applicationId);
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withPathParams(pathParams)
                .withBody(themeData)
                .put(APPLICATION_THEME_ENDPOINT);
    }
}
