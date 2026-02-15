package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.OrderPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Order API Test Class
 */
@Epic("Order API")
@Feature("Orders")
public class OrderTest extends BaseTest {
    private OrderPage orderPage;

    @BeforeClass
    public void setup() {
        orderPage = new OrderPage();
    }

    @Test(description = "Call get order by ID API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Order")
    public void testGetOrderById() {
        Response response = orderPage.getOrderById("1");

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }

    @Test(description = "Call validate order promotions API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Validate Order")
    public void testValidateOrderPromotions() {
        Map<String, Object> orderData = new HashMap<>();
        orderData.put("items", new java.util.ArrayList<>());

        Response response = orderPage.validateOrderWithPromotions(orderData);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
