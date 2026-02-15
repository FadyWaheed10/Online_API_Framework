package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Promotion Page Object Model - V1
 * Encapsulates all promotion-related API endpoints
 */
public class PromotionPage extends ApiClient {

    private static final String PROMOTIONS_ENDPOINT = "/promotions";

    public Response createPromotion(Object promotionData, String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withBody(promotionData)
                .post(PROMOTIONS_ENDPOINT);
    }

    public Response listPromotions() {
        return withoutAuth()
                .get(PROMOTIONS_ENDPOINT);
    }
}
