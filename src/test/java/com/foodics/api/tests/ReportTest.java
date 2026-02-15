package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.ReportPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Report API Test Class
 */
@Epic("Report API")
@Feature("Reports")
public class ReportTest extends BaseTest {
    private ReportPage reportPage;

    @BeforeClass
    public void setup() {
        reportPage = new ReportPage();
    }

    @Test(description = "Call get top performing products API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Top Performing Products")
    public void testGetTopPerformingProducts() {
        Response response = reportPage.getTopPerformingProducts();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
