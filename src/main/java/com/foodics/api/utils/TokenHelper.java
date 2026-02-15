package com.foodics.api.utils;

import com.foodics.api.auth.AuthManager;
import com.foodics.api.auth.Role;
import com.foodics.api.pom.AuthPage;
import com.foodics.api.models.LoginRequest;
import io.restassured.response.Response;

/**
 * Token Helper Utility
 * Provides convenient methods to get authentication tokens
 */
public class TokenHelper {
    
    /**
     * Get token for a specific role
     * @param role User role
     * @return Authentication token
     */
    public static String getTokenForRole(Role role) {
        AuthManager authManager = AuthManager.getInstance();
        return authManager.getTokenForRole(role);
    }
    
    /**
     * Get token using credentials
     * @param email User email
     * @param password User password
     * @return Authentication token
     */
    public static String getToken(String email, String password) {
        AuthManager authManager = AuthManager.getInstance();
        return authManager.getToken(email, password);
    }
    
    /**
     * Get token and print it to console (useful for manual testing)
     * @param email User email
     * @param password User password
     * @return Authentication token
     */
    public static String getTokenAndPrint(String email, String password) {
        String token = getToken(email, password);
        System.out.println("========================================");
        System.out.println("Authentication Token:");
        System.out.println(token);
        System.out.println("========================================");
        return token;
    }
    
    /**
     * Get token for a role and print it to console
     * @param role User role
     * @return Authentication token
     */
    public static String getTokenForRoleAndPrint(Role role) {
        String token = getTokenForRole(role);
        System.out.println("========================================");
        System.out.println("Token for role: " + role.name());
        System.out.println(token);
        System.out.println("========================================");
        return token;
    }
    
    /**
     * Test login and return full response
     * Useful for debugging authentication issues
     * @param email User email
     * @param password User password
     * @return Login API response
     */
    public static Response testLogin(String email, String password) {
        AuthPage authPage = new AuthPage();
        LoginRequest loginRequest = new LoginRequest();
        // Try username first (if it doesn't contain @), otherwise use email
        if (email != null && !email.contains("@")) {
            loginRequest.setUsername(email);
        } else {
            loginRequest.setEmail(email);
        }
        loginRequest.setPassword(password);
        
        Response response = authPage.login(loginRequest);
        
        System.out.println("========================================");
        System.out.println("Login API Response:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("========================================");
        
        return response;
    }
}
