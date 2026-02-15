package com.foodics.api.pom.v1;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Modifier Page Object Model - V1
 * Encapsulates all modifier-related API endpoints
 */
public class ModifierPage extends ApiClient {

    private static final String MODIFIER_ENDPOINT = "/modifier-groups/{modifierGroupId}/modifiers/{modifierId}";

    public Response createModifier(String modifierGroupId, String modifierId, Object modifierData) {
        Map<String, Object> pathParams = Map.of("modifierGroupId", modifierGroupId, "modifierId", modifierId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(modifierData)
                .post(MODIFIER_ENDPOINT);
    }

    public Response updateModifier(String modifierGroupId, String modifierId, Object modifierData) {
        Map<String, Object> pathParams = Map.of("modifierGroupId", modifierGroupId, "modifierId", modifierId);
        return withoutAuth()
                .withPathParams(pathParams)
                .withBody(modifierData)
                .patch(MODIFIER_ENDPOINT);
    }
}
