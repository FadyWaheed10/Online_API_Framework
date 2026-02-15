package com.foodics.api.tests;

import com.foodics.api.auth.Role;
import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.UserPage;
import com.foodics.api.utils.ResponseValidator;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Who Am I API Test Class
 * Tests the endpoint that returns current user information
 */
@Epic("User API")
@Feature("Who Am I")
public class WhoAmITest extends BaseTest {
    private UserPage userPage;
    private String token;

    @BeforeClass
    public void setup() {
        userPage = new UserPage();
        // Get token for authentication
        ConfigManager config = ConfigManager.getInstance();
        String email = config.getProperty("test.user.email");
        String password = config.getProperty("test.user.password");
        token = TokenHelper.getToken(email, password);
    }

    @Test(description = "Verify who am I endpoint returns current user information")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Current User Info")
    public void testWhoAmI() {
        // Get concept key using helper (will extract from login response automatically)
        com.foodics.api.utils.ConceptKeyHelper.setConceptKey(null); // Clear cache to force refresh
        String conceptKey = com.foodics.api.utils.ConceptKeyHelper.getConceptKey(token);
        
        // Act - Call whoAmI with token and concept key
        Response response = userPage.whoAmI(token, conceptKey);
        
        // Print response for visibility
        System.out.println("========================================");
        System.out.println("Who Am I Response:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().prettyPrint());
        System.out.println("========================================");
        
        // Assert
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "data");
    }

    @Test(description = "Verify who am I endpoint using role-based auth")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Current User Info")
    public void testWhoAmIWithRole() {
        // Get concept key (using first concept from login response)
        String conceptKey = "1"; // Default - you may need to get this from config or login response
        
        // Act
        Response response = userPage.whoAmI(Role.USER, conceptKey);

        // Assert
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "data");
    }
}
