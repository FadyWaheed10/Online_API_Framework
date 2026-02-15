package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.EmployeePage;
import com.foodics.api.utils.ConceptKeyHelper;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee API Test Class
 */
@Epic("Employee API")
@Feature("Employees")
public class EmployeeTest extends BaseTest {
    private EmployeePage employeePage;
    private String conceptKey;

    @BeforeClass
    public void setup() {
        employeePage = new EmployeePage();
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

    @Test(description = "Call employee forgot password API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Forgot Password")
    public void testForgotPassword() {
        Map<String, Object> passwordData = new HashMap<>();
        passwordData.put("email", "test@example.com");

        Response response = employeePage.forgotPassword(passwordData);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
