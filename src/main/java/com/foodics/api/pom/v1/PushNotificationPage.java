package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Push Notification Page Object Model - V1
 * Encapsulates all push notification-related API endpoints
 */
public class PushNotificationPage extends ApiClient {

    private static final String PUSH_NOTIFICATIONS_ENDPOINT = "/push-notifications";

    public Response sendNotification(Object notificationData, String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withBody(notificationData)
                .post(PUSH_NOTIFICATIONS_ENDPOINT);
    }

    public Response listNotifications(String conceptKey) {
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .get(PUSH_NOTIFICATIONS_ENDPOINT);
    }
}
