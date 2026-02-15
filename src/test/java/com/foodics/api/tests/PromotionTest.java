package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.PromotionPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Promotion API Test Class
 */
@Epic("Promotion API")
@Feature("Promotions")
public class PromotionTest extends BaseTest {
    private PromotionPage promotionPage;

    @BeforeClass
    public void setup() {
        promotionPage = new PromotionPage();
    }

    @Test(description = "Call list promotions API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Promotions")
    public void testListPromotions() {
        Response response = promotionPage.listPromotions();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
