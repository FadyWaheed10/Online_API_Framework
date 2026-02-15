package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.AuthPage;
import com.foodics.api.utils.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication API Test Class
 * Example test class for authentication endpoints
 */
@Epic("Authentication API")
@Feature("User Authentication")
public class AuthTest extends BaseTest {
    private AuthPage authPage;

    @BeforeClass
    public void setup() {
        authPage = new AuthPage();
    }

    @Test(description = "Verify successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Login")
    public void testLoginSuccess() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "test@example.com");
        credentials.put("password", "password123");

        // Act
        Response response = authPage.login(credentials);

        // Assert
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "data.token");
        ResponseValidator.validateFieldExists(response, "data.refreshToken");
    }

    @Test(description = "Verify login fails with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Login")
    public void testLoginFailure() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "invalid@example.com");
        credentials.put("password", "wrongpassword");

        // Act
        Response response = authPage.login(credentials);

        // Assert
        ResponseValidator.validateStatusCode(response, 401);
    }

    @Test(description = "Verify guest login")
    @Severity(SeverityLevel.NORMAL)
    @Story("Guest Login")
    public void testGuestLogin() {
        // Arrange
        Map<String, Object> guestData = new HashMap<>();
        guestData.put("username", "966535849161");
        guestData.put("password", 12345678);

        // Act
        Response response = authPage.guestLogin(guestData);

        // Assert
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "data");
    }
}
