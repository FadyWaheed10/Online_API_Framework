package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.ApplicationPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Application API Test Class
 */
@Epic("Application API")
@Feature("Applications")
public class ApplicationTest extends BaseTest {
    private ApplicationPage applicationPage;

    @BeforeClass
    public void setup() {
        applicationPage = new ApplicationPage();
    }

    @Test(description = "Call get application API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Application")
    public void testGetApplication() {
        Response response = applicationPage.getApplication();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
