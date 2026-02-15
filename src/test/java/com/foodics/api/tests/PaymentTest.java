package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.PaymentPage;
import com.foodics.api.utils.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Payment API Test Class - V1
 * Example test class demonstrating how to use the POM pattern
 */
@Epic("Payment API")
@Feature("Payment Management")
public class PaymentTest extends BaseTest {
    private PaymentPage paymentPage;

    @BeforeClass
    public void setup() {
        paymentPage = new PaymentPage();
    }

    @Test(description = "Verify getting payment by order token")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Payment")
    public void testGetPaymentByOrderToken() {
        // Arrange
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("orderToken", "test-order-token");

        // Act
        Response response = paymentPage.getPaymentByOrderToken(queryParams);

        // Assert
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "data");
    }
}
