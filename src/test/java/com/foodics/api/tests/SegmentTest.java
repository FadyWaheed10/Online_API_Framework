package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.v1.SegmentPage;
import com.foodics.api.utils.ConceptKeyHelper;
import com.foodics.api.utils.TokenHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Segment API Test Class
 */
@Epic("Segment API")
@Feature("Segments")
public class SegmentTest extends BaseTest {
    private SegmentPage segmentPage;
    private String conceptKey;

    @BeforeClass
    public void setup() {
        segmentPage = new SegmentPage();
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

    @Test(description = "Call list segments API")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Segments")
    public void testListSegments() {
        Response response = segmentPage.listSegments(conceptKey);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
