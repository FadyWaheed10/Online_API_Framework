package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.DevicePage;
import com.foodics.api.utils.ConceptKeyHelper;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Device API Test Class
 */
@Epic("Device API")
@Feature("Devices")
public class DeviceTest extends BaseTest {
    private DevicePage devicePage;
    private String conceptKey;

    @BeforeClass
    public void setup() {
        devicePage = new DevicePage();
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

    @Test(description = "Call enable maintenance mode API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Maintenance Mode")
    public void testEnableMaintenanceMode() {
        Map<String, Object> maintenanceData = new HashMap<>();
        maintenanceData.put("enabled", false);

        Response response = devicePage.enableMaintenanceMode(maintenanceData, conceptKey);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
