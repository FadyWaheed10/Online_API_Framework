package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Concept Page Object Model - V1
 * Encapsulates all concept-related API endpoints
 */
public class ConceptPage extends ApiClient {

    private static final String CONCEPTS_ENDPOINT = "/concepts";
    private static final String CONCEPT_BY_ID_ENDPOINT = "/concepts/{id}";
    private static final String REGISTER_CONCEPT_ENDPOINT = "/register";

    public Response createConcept(Object conceptData) {
        return withoutAuth()
                .withBody(conceptData)
                .post(REGISTER_CONCEPT_ENDPOINT);
    }

    public Response updateConcept(String conceptId, Object conceptData, String conceptKey) {
        Map<String, Object> pathParams = Map.of("id", conceptId);
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withPathParams(pathParams)
                .withBody(conceptData)
                .patch(CONCEPT_BY_ID_ENDPOINT);
    }

    public Response listConcepts() {
        return withoutAuth()
                .get(CONCEPTS_ENDPOINT);
    }
}
