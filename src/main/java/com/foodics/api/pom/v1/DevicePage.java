package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Device Page Object Model - V1
 * Encapsulates all device-related API endpoints
 */
public class DevicePage extends ApiClient {

    private static final String ENABLE_MAINTENANCE_MODE_ENDPOINT = "/api/devices/enable-maintenance-mode";

    public Response enableMaintenanceMode(Object maintenanceData, String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withBody(maintenanceData)
                .patch(ENABLE_MAINTENANCE_MODE_ENDPOINT);
    }
}
