package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.CmsPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * CMS API Test Class
 */
@Epic("CMS API")
@Feature("Content Management")
public class CmsTest extends BaseTest {
    private CmsPage cmsPage;

    @BeforeClass
    public void setup() {
        cmsPage = new CmsPage();
    }

    @Test(description = "Call list CMS pages API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List CMS Pages")
    public void testListCmsPages() {
        Response response = cmsPage.listCmsPages();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }

    @Test(description = "Call get sliders API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Sliders")
    public void testGetSliders() {
        Response response = cmsPage.getSliders();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
