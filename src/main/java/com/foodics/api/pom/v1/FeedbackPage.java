package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Feedback Page Object Model - V1
 * Encapsulates all feedback-related API endpoints
 */
public class FeedbackPage extends ApiClient {

    private static final String FEEDBACK_ENDPOINT = "/feedback";

    public Response createFeedback(Object feedbackData) {
        return withoutAuth()
                .withBody(feedbackData)
                .post(FEEDBACK_ENDPOINT);
    }
}
