package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.ModifierPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Modifier API Test Class
 */
@Epic("Modifier API")
@Feature("Modifiers")
public class ModifierTest extends BaseTest {
    private ModifierPage modifierPage;

    @BeforeClass
    public void setup() {
        modifierPage = new ModifierPage();
    }

    @Test(description = "Call create modifier API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Create Modifier")
    public void testCreateModifier() {
        Map<String, Object> modifierData = new HashMap<>();
        modifierData.put("name", "Test modifier");
        modifierData.put("price", 100);

        Response response = modifierPage.createModifier("1", "1", modifierData);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
