package com.foodics.api.base;

import com.foodics.api.auth.AuthManager;
import com.foodics.api.config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Base Test Class
 * All test classes should extend this class
 * Provides common setup and teardown methods
 */
public class BaseTest {
    protected ConfigManager configManager;
    protected AuthManager authManager;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize configuration
        configManager = ConfigManager.getInstance();
        
        // Set base URI
        RestAssured.baseURI = configManager.getBaseUrl();
        
        // Enable logging if needed (uncomment for debugging)
        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeMethod
    public void beforeMethod() {
        // Initialize auth manager
        authManager = AuthManager.getInstance();
    }

    @AfterMethod
    public void afterMethod() {
        // Clean up if needed
        // For example, clear tokens after each test
        // authManager.clearTokens();
    }
}
