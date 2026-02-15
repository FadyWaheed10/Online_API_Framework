package com.foodics.api.pom;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import com.foodics.api.utils.ConceptKeyHelper;
import io.restassured.response.Response;

import java.util.Map;

/**
 * User Page Object Model
 * Encapsulates all user-related API endpoints
 * 
 * Based on Postman collection analysis, this class should contain:
 * - Get user profile
 * - Update user profile
 * - Get user list (for admin)
 * - Delete user
 * - Update user roles/permissions
 * etc.
 */
public class UserPage extends ApiClient {

    private static final String USER_PROFILE_ENDPOINT = "/api/v1/users/profile";
    private static final String USER_BY_ID_ENDPOINT = "/api/v1/users/{id}";
    private static final String USERS_LIST_ENDPOINT = "/api/v1/users";
    private static final String UPDATE_USER_ROLE_ENDPOINT = "/api/v1/users/{id}/role";
    private static final String WHO_AM_I_ENDPOINT = "/whoami";

    /**
     * Get current user information (Who am I)
     * @param token Authentication token
     * @param conceptKey Concept key for Solo-Concept header (optional, will be extracted if null)
     * @return API response
     */
    public Response whoAmI(String token, String conceptKey) {
        String key = conceptKey != null ? conceptKey : ConceptKeyHelper.getConceptKey(token);
        return withAuth(token)
                .withConceptKey(key)
                .get(WHO_AM_I_ENDPOINT);
    }

    /**
     * Get current user information (Who am I) using role
     * @param role User role for authentication
     * @param conceptKey Concept key for Solo-Concept header (optional, will be extracted if null)
     * @return API response
     */
    public Response whoAmI(Role role, String conceptKey) {
        String key = conceptKey != null ? conceptKey : ConceptKeyHelper.getConceptKey(null);
        return withAuth(role)
                .withConceptKey(key)
                .get(WHO_AM_I_ENDPOINT);
    }

    /**
     * Get current user profile
     * @param role User role for authentication
     * @return API response
     */
    public Response getUserProfile(Role role) {
        return withAuth(role)
                .get(USER_PROFILE_ENDPOINT);
    }

    /**
     * Update user profile
     * @param role User role for authentication
     * @param userData Updated user data
     * @return API response
     */
    public Response updateUserProfile(Role role, Object userData) {
        return withAuth(role)
                .withBody(userData)
                .put(USER_PROFILE_ENDPOINT);
    }

    /**
     * Get user by ID
     * @param role User role for authentication
     * @param userId User ID
     * @return API response
     */
    public Response getUserById(Role role, String userId) {
        Map<String, Object> pathParams = Map.of("id", userId);
        return withAuth(role)
                .withPathParams(pathParams)
                .get(USER_BY_ID_ENDPOINT);
    }

    /**
     * Get list of users (typically admin only)
     * @param role User role for authentication
     * @param queryParams Query parameters (e.g., page, limit, filters)
     * @return API response
     */
    public Response getUsersList(Role role, Map<String, Object> queryParams) {
        return withAuth(role)
                .withQueryParams(queryParams)
                .get(USERS_LIST_ENDPOINT);
    }

    /**
     * Delete user
     * @param role User role for authentication
     * @param userId User ID
     * @return API response
     */
    public Response deleteUser(Role role, String userId) {
        Map<String, Object> pathParams = Map.of("id", userId);
        return withAuth(role)
                .withPathParams(pathParams)
                .delete(USER_BY_ID_ENDPOINT);
    }

    /**
     * Update user role (typically admin only)
     * @param role User role for authentication
     * @param userId User ID
     * @param roleData New role data
     * @return API response
     */
    public Response updateUserRole(Role role, String userId, Object roleData) {
        Map<String, Object> pathParams = Map.of("id", userId);
        return withAuth(role)
                .withPathParams(pathParams)
                .withBody(roleData)
                .put(UPDATE_USER_ROLE_ENDPOINT);
    }
}
