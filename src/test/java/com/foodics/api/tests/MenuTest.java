package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.MenuPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Menu API Test Class
 */
@Epic("Menu API")
@Feature("Menus")
public class MenuTest extends BaseTest {
    private MenuPage menuPage;

    @BeforeClass
    public void setup() {
        menuPage = new MenuPage();
    }

    @Test(description = "Call get menus API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Menus")
    public void testGetMenus() {
        Response response = menuPage.getMenus();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }

    @Test(description = "Call get menu items API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Menu Items")
    public void testGetMenuItems() {
        Response response = menuPage.getMenuItems("1");

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
