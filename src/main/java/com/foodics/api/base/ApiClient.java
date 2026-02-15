package com.foodics.api.base;

import com.foodics.api.auth.AuthManager;
import com.foodics.api.auth.Role;
import com.foodics.api.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * Base API Client
 * Provides common functionality for all API requests
 * Follows POM pattern - acts as base page object
 */
public class ApiClient {
    protected RequestSpecification requestSpec;
    protected ConfigManager configManager;
    protected AuthManager authManager;

    public ApiClient() {
        configManager = ConfigManager.getInstance();
        authManager = AuthManager.getInstance();
        initializeRequestSpec();
    }

    /**
     * Initialize base request specification
     */
    private void initializeRequestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(configManager.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    /**
     * Set authentication for the request
     * @param role User role
     * @return ApiClient instance for method chaining
     */
    public ApiClient withAuth(Role role) {
        Headers authHeaders = authManager.getAuthHeaders(role);
        requestSpec = RestAssured.given()
                .spec(requestSpec)
                .headers(authHeaders);
        return this;
    }

    /**
     * Set authentication with custom token
     * @param token Custom authentication token
     * @return ApiClient instance for method chaining
     */
    public ApiClient withAuth(String token) {
        Headers authHeaders = authManager.getAuthHeaders(token);
        requestSpec = RestAssured.given()
                .spec(requestSpec)
                .headers(authHeaders);
        return this;
    }

    /**
     * Set request without authentication
     * @return ApiClient instance for method chaining
     */
    public ApiClient withoutAuth() {
        requestSpec = RestAssured.given()
                .spec(requestSpec);
        return this;
    }

    /**
     * Add Solo-Concept header for concept-specific API operations
     * @param conceptKey Concept key for the Solo-Concept header
     * @return ApiClient instance for method chaining
     */
    public ApiClient withConceptKey(String conceptKey) {
        if (conceptKey != null && !conceptKey.isEmpty()) {
            requestSpec = requestSpec.header("Solo-Concept", conceptKey);
        }
        return this;
    }

    /**
     * Add custom headers
     * @param headers Map of header key-value pairs
     * @return ApiClient instance for method chaining
     */
    public ApiClient withHeaders(Map<String, String> headers) {
        requestSpec = requestSpec.headers(headers);
        return this;
    }

    /**
     * Add query parameters
     * @param params Map of query parameter key-value pairs
     * @return ApiClient instance for method chaining
     */
    public ApiClient withQueryParams(Map<String, Object> params) {
        requestSpec = requestSpec.queryParams(params);
        return this;
    }

    /**
     * Add path parameters
     * @param params Map of path parameter key-value pairs
     * @return ApiClient instance for method chaining
     */
    public ApiClient withPathParams(Map<String, Object> params) {
        requestSpec = requestSpec.pathParams(params);
        return this;
    }

    /**
     * Set request body
     * @param body Request body object
     * @return ApiClient instance for method chaining
     */
    public ApiClient withBody(Object body) {
        requestSpec = requestSpec.body(body);
        return this;
    }

    /**
     * Perform GET request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response get(String endpoint) {
        return requestSpec.when().get(endpoint);
    }

    /**
     * Perform POST request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response post(String endpoint) {
        return requestSpec.when().post(endpoint);
    }

    /**
     * Perform PUT request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response put(String endpoint) {
        return requestSpec.when().put(endpoint);
    }

    /**
     * Perform PATCH request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response patch(String endpoint) {
        return requestSpec.when().patch(endpoint);
    }

    /**
     * Perform DELETE request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response delete(String endpoint) {
        return requestSpec.when().delete(endpoint);
    }

    /**
     * Get current request specification
     * @return RequestSpecification object
     */
    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }
}
