package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Segment Page Object Model - V1
 * Encapsulates all segment-related API endpoints
 */
public class SegmentPage extends ApiClient {

    private static final String SEGMENTS_ENDPOINT = "/segments";

    public Response listSegments(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .get(SEGMENTS_ENDPOINT);
    }
}
