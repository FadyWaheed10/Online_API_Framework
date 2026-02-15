package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Payment Page Object Model - V1
 * Encapsulates all payment-related API endpoints
 */
public class PaymentPage extends ApiClient {

    private static final String PAYMENT_BY_ORDER_TOKEN_ENDPOINT = "/payments/orders/ordertoken";

    public Response getPaymentByOrderToken(Map<String, Object> queryParams) {
        return withoutAuth()
                .withQueryParams(queryParams)
                .get(PAYMENT_BY_ORDER_TOKEN_ENDPOINT);
    }
}
