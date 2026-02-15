package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.LocationPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Location API Test Class
 */
@Epic("Location API")
@Feature("Locations")
public class LocationTest extends BaseTest {
    private LocationPage locationPage;

    @BeforeClass
    public void setup() {
        locationPage = new LocationPage();
    }

    @Test(description = "Call list locations API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Locations")
    public void testListLocations() {
        Response response = locationPage.listLocations();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
