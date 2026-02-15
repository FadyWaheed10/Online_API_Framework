package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.ConceptPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Concept API Test Class
 */
@Epic("Concept API")
@Feature("Concepts")
public class ConceptTest extends BaseTest {
    private ConceptPage conceptPage;

    @BeforeClass
    public void setup() {
        conceptPage = new ConceptPage();
    }

    @Test(description = "Call list concepts API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Concepts")
    public void testListConcepts() {
        Response response = conceptPage.listConcepts();

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
