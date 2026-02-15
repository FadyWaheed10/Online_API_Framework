package com.foodics.api.tests;

import com.foodics.api.auth.Role;
import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import org.testng.annotations.Test;

/**
 * Test class to get authentication tokens
 * This can be run to retrieve tokens for manual use or debugging
 */
@Epic("Authentication")
@Feature("Token Retrieval")
public class GetTokenTest extends BaseTest {

    @Test(description = "Get token using credentials from config")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Token")
    public void testGetToken() {
        ConfigManager config = ConfigManager.getInstance();
        String email = config.getProperty("test.user.email");
        String password = config.getProperty("test.user.password");
        
        if (email == null || password == null) {
            System.out.println("Please set test.user.email and test.user.password in config file");
            return;
        }
        
        TokenHelper.getTokenAndPrint(email, password);
        System.out.println("Token retrieved successfully!");
    }

    @Test(description = "Get token for USER role")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Token by Role")
    public void testGetTokenForUserRole() {
        TokenHelper.getTokenForRoleAndPrint(Role.USER);
        System.out.println("Token for USER role retrieved successfully!");
    }

    @Test(description = "Get token for ADMIN role")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Token by Role")
    public void testGetTokenForAdminRole() {
        TokenHelper.getTokenForRoleAndPrint(Role.ADMIN);
        System.out.println("Token for ADMIN role retrieved successfully!");
    }

    @Test(description = "Test login API and print full response")
    @Severity(SeverityLevel.NORMAL)
    @Story("Debug Login")
    public void testLoginAndPrintResponse() {
        ConfigManager config = ConfigManager.getInstance();
        String email = config.getProperty("test.user.email");
        String password = config.getProperty("test.user.password");
        
        if (email == null || password == null) {
            System.out.println("Please set test.user.email and test.user.password in config file");
            return;
        }
        
        TokenHelper.testLogin(email, password);
        System.out.println("Login test completed!");
    }
}
