package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Client Page Object Model - V1
 * Encapsulates all client-related API endpoints
 */
public class ClientPage extends ApiClient {

    private static final String CLIENTS_ENDPOINT = "/clients";

    public Response listClients(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .get(CLIENTS_ENDPOINT);
    }
}
