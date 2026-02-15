package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Report Page Object Model - V1
 * Encapsulates all report-related API endpoints
 */
public class ReportPage extends ApiClient {

    private static final String TOP_PERFORMING_PRODUCTS_ENDPOINT = "/reports/top-performing-products";

    public Response getTopPerformingProducts() {
        return withoutAuth()
                .get(TOP_PERFORMING_PRODUCTS_ENDPOINT);
    }
}
