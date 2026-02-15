package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Customer Page Object Model - V1
 * Encapsulates all customer-related API endpoints
 */
public class CustomerPage extends ApiClient {

    private static final String CUSTOMERS_ENDPOINT = "/customers";
    private static final String CUSTOMER_BY_ID_ENDPOINT = "/customers/{customerId}";
    private static final String CUSTOMER_ORDERS_ENDPOINT = "/customers/{customerId}/orders";
    private static final String CUSTOMER_ADDRESSES_ENDPOINT = "/customers/{customerId}/addresses";
    private static final String CUSTOMER_ADDRESS_BY_ID_ENDPOINT = "/customers/{customerId}/addresses/{addressId}";
    private static final String CUSTOMER_VEHICLES_ENDPOINT = "/customers/{customerId}/cars";
    private static final String VERIFY_OTP_ENDPOINT = "/customers/{customerId}/verify";

    // Customer Management
    public Response createCustomer(Object customerData) {
        return withoutAuth()
                .withBody(customerData)
                .post(CUSTOMERS_ENDPOINT);
    }

    public Response getCustomer(String customerId, String conceptKey) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withPathParams(pathParams)
                .get(CUSTOMER_BY_ID_ENDPOINT);
    }

    public Response updateCustomer(String customerId, Object customerData) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(customerData)
                .patch(CUSTOMER_BY_ID_ENDPOINT);
    }

    public Response deleteCustomer(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .delete(CUSTOMERS_ENDPOINT);
    }

    public Response listCustomers(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .get(CUSTOMERS_ENDPOINT);
    }

    public Response listCustomerOrders(String customerId) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(CUSTOMER_ORDERS_ENDPOINT);
    }

    // Customer Addresses
    public Response createCustomerAddress(String customerId, Object addressData) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(addressData)
                .post(CUSTOMER_ADDRESSES_ENDPOINT);
    }

    public Response updateCustomerAddress(String customerId, String addressId, Object addressData) {
        Map<String, Object> pathParams = Map.of("customerId", customerId, "addressId", addressId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(addressData)
                .patch(CUSTOMER_ADDRESS_BY_ID_ENDPOINT);
    }

    public Response deleteCustomerAddress(String customerId, String addressId) {
        Map<String, Object> pathParams = Map.of("customerId", customerId, "addressId", addressId);
        return withoutAuth()
                .withPathParams(pathParams)
                .delete(CUSTOMER_ADDRESS_BY_ID_ENDPOINT);
    }

    public Response listCustomerAddresses(String customerId, String conceptKey) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withPathParams(pathParams)
                .get(CUSTOMER_ADDRESSES_ENDPOINT);
    }

    // Customer Vehicles
    public Response createCustomerVehicle(String customerId, Object vehicleData) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(vehicleData)
                .post(CUSTOMER_VEHICLES_ENDPOINT);
    }

    public Response listCustomerVehicles(String customerId) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .get(CUSTOMER_VEHICLES_ENDPOINT);
    }

    // OTP Verification
    public Response verifyOTP(String customerId, Object otpData) {
        Map<String, Object> pathParams = Map.of("customerId", customerId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(otpData)
                .post(VERIFY_OTP_ENDPOINT);
    }
}
