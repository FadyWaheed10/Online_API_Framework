package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.ClientPage;
import com.foodics.api.utils.ConceptKeyHelper;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Client API Test Class
 */
@Epic("Client API")
@Feature("Clients")
public class ClientTest extends BaseTest {
    private ClientPage clientPage;
    private String conceptKey;

    @BeforeClass
    public void setup() {
        clientPage = new ClientPage();
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

    @Test(description = "Call list clients API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Clients")
    public void testListClients() {
        Response response = clientPage.listClients(conceptKey);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
