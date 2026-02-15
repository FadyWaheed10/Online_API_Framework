package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.LicensePage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * License API Test Class
 */
@Epic("License API")
@Feature("Licenses")
public class LicenseTest extends BaseTest {
    private LicensePage licensePage;
    private String apiKey;

    @BeforeClass
    public void setup() {
        licensePage = new LicensePage();
        apiKey = ConfigManager.getInstance().getApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = "test-api-key";
        }
    }

    @Test(description = "Call get license by ID API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get License")
    public void testGetLicense() {
        Response response = licensePage.getLicense("1", apiKey);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
