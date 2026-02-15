package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.CustomerPage;
import com.foodics.api.utils.ConceptKeyHelper;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Customer API Test Class
 */
@Epic("Customer API")
@Feature("Customers")
public class CustomerTest extends BaseTest {
    private CustomerPage customerPage;
    private String conceptKey;

    @BeforeClass
    public void setup() {
        customerPage = new CustomerPage();
        ConfigManager config = ConfigManager.getInstance();
        String email = config.getProperty("test.user.email");
        String password = config.getProperty("test.user.password");
        if (email != null && password != null) {
            String token = TokenHelper.getToken(email, password);
            conceptKey = ConceptKeyHelper.getConceptKey(token);
        } else {
            conceptKey = "1";
        }
    }

    @Test(description = "Call create customer API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Create Customer")
    public void testCreateCustomer() {
        java.util.Map<String, Object> customerData = new java.util.HashMap<>();
        customerData.put("name", "Test Customer");
        customerData.put("email", "testcustomer@example.com");
        customerData.put("phone", "1234567890");

        Response response = customerPage.createCustomer(customerData);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
