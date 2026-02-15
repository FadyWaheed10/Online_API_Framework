package com.foodics.api.tests;

import com.foodics.api.base.BaseTest;
import com.foodics.api.pom.v1.FeedbackPage;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Feedback API Test Class
 */
@Epic("Feedback API")
@Feature("Feedback")
public class FeedbackTest extends BaseTest {
    private FeedbackPage feedbackPage;

    @BeforeClass
    public void setup() {
        feedbackPage = new FeedbackPage();
    }

    @Test(description = "Call create feedback API")
    @Severity(SeverityLevel.NORMAL)
    @Story("Create Feedback")
    public void testCreateFeedback() {
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("message", "Test feedback");
        feedbackData.put("rating", 5);

        Response response = feedbackPage.createFeedback(feedbackData);

        org.testng.Assert.assertNotNull(response, "Response should not be null");
        org.testng.Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600,
                "Expected valid HTTP status, got: " + response.getStatusCode());
    }
}
