package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Order Page Object Model - V1
 * Encapsulates all order-related API endpoints
 */
public class OrderPage extends ApiClient {

    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String ORDER_BY_ID_ENDPOINT = "/orders/{id}";
    private static final String VALIDATE_ORDER_ENDPOINT = "/orders/validate";
    private static final String VALIDATE_ORDER_PROMOTIONS_ENDPOINT = "/promotions/validation";
    private static final String CANCEL_ORDER_ENDPOINT = "/orders/{id}/cancellation";
    private static final String ORDER_DRIVER_DETAILS_ENDPOINT = "/orders/{id}/employees";
    private static final String ORDER_PAYMENTS_ENDPOINT = "/orders/{orderId}/payments";
    private static final String UPDATE_DRIVER_LOCATION_ENDPOINT = "/employees/{id}/bearings";

    public Response createOrder(Object orderData) {
        return withoutAuth()
                .withBody(orderData)
                .post(ORDERS_ENDPOINT);
    }

    public Response getOrderById(String orderId) {
        Map<String, Object> pathParams = Map.of("id", orderId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(ORDER_BY_ID_ENDPOINT);
    }

    public Response validateOrder(Object orderData, String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withBody(orderData)
                .post(VALIDATE_ORDER_ENDPOINT);
    }

    public Response validateOrderWithPromotions(Object orderData) {
        return withoutAuth()
                .withBody(orderData)
                .post(VALIDATE_ORDER_PROMOTIONS_ENDPOINT);
    }

    public Response cancelOrder(String orderId, Object cancellationData) {
        Map<String, Object> pathParams = Map.of("id", orderId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(cancellationData)
                .patch(CANCEL_ORDER_ENDPOINT);
    }

    public Response getOrderDriverDetails(String orderId) {
        Map<String, Object> pathParams = Map.of("id", orderId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(ORDER_DRIVER_DETAILS_ENDPOINT);
    }

    public Response createOrderPayment(String orderId, Object paymentData) {
        Map<String, Object> pathParams = Map.of("orderId", orderId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(paymentData)
                .post(ORDER_PAYMENTS_ENDPOINT);
    }

    public Response updateDriverLocation(String employeeId, Object locationData) {
        Map<String, Object> pathParams = Map.of("id", employeeId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(locationData)
                .post(UPDATE_DRIVER_LOCATION_ENDPOINT);
    }
}
