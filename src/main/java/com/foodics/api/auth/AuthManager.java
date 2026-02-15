package com.foodics.api.auth;

import com.foodics.api.config.ConfigManager;
import com.foodics.api.models.LoginRequest;
import com.foodics.api.models.LoginResponse;
import com.foodics.api.pom.AuthPage;
import com.foodics.api.utils.JsonUtils;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authentication Manager
 * Handles different authentication methods and role-based access
 */
public class AuthManager {
    private static AuthManager instance;
    private Map<String, String> tokens;
    private ConfigManager configManager;

    private AuthManager() {
        tokens = new HashMap<>();
        configManager = ConfigManager.getInstance();
    }

    public static AuthManager getInstance() {
        if (instance == null) {
            synchronized (AuthManager.class) {
                if (instance == null) {
                    instance = new AuthManager();
                }
            }
        }
        return instance;
    }

    /**
     * Get authentication headers for a specific role
     * @param role User role (e.g., ADMIN, USER, MERCHANT)
     * @return Headers with authentication token
     */
    public Headers getAuthHeaders(Role role) {
        String token = getTokenForRole(role);
        List<Header> headers = new ArrayList<>();
        
        // Add Bearer token
        headers.add(new Header("Authorization", "Bearer " + token));
        
        // Add API key if required
        String apiKey = configManager.getApiKey();
        if (apiKey != null && !apiKey.isEmpty()) {
            headers.add(new Header("X-API-Key", apiKey));
        }
        
        return new Headers(headers);
    }

    /**
     * Get authentication headers with custom token
     * @param token Custom authentication token
     * @return Headers with authentication token
     */
    public Headers getAuthHeaders(String token) {
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("Authorization", "Bearer " + token));
        
        String apiKey = configManager.getApiKey();
        if (apiKey != null && !apiKey.isEmpty()) {
            headers.add(new Header("X-API-Key", apiKey));
        }
        
        return new Headers(headers);
    }

    /**
     * Get token for a specific role
     * This should be implemented based on your authentication flow
     * @param role User role
     * @return Authentication token
     */
    public String getTokenForRole(Role role) {
        // Check if token is cached
        if (tokens.containsKey(role.name())) {
            return tokens.get(role.name());
        }

        // If not cached, fetch from config or authenticate
        String configKey = "auth.token." + role.name().toLowerCase();
        String token = configManager.getProperty(configKey);
        if (token == null || token.isEmpty()) {
            // Perform authentication and get token
            token = authenticate(role);
        }

        // Cache the token
        tokens.put(role.name(), token);
        return token;
    }

    /**
     * Authenticate and get token for a role
     * This method calls the login API endpoint to get a fresh token
     * @param role User role
     * @return Authentication token
     */
    private String authenticate(Role role) {
        // First check if credentials are in config
        String emailKey = "test." + role.name().toLowerCase() + ".email";
        String passwordKey = "test." + role.name().toLowerCase() + ".password";
        
        String email = configManager.getProperty(emailKey);
        String password = configManager.getProperty(passwordKey);
        
        // If credentials not found, try default test credentials
        if (email == null || email.isEmpty()) {
            email = configManager.getProperty("test.user.email");
        }
        if (password == null || password.isEmpty()) {
            password = configManager.getProperty("test.user.password");
        }
        
        // If still no credentials, return from config token
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            String configKey = "auth.token." + role.name().toLowerCase();
            String token = configManager.getProperty(configKey);
            return token != null && !token.isEmpty() ? token : configManager.getAuthToken();
        }
        
        // Call login API
        try {
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
            
            if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
                // Try multiple JSON paths to extract token (check data.attributes.token first)
                String token = response.jsonPath().getString("data.attributes.token");
                if (token == null || token.isEmpty()) {
                    token = response.jsonPath().getString("data.token");
                }
                if (token == null || token.isEmpty()) {
                    token = response.jsonPath().getString("data.accessToken");
                }
                if (token == null || token.isEmpty()) {
                    token = response.jsonPath().getString("token");
                }
                if (token == null || token.isEmpty()) {
                    token = response.jsonPath().getString("accessToken");
                }
                
                // If still not found, try parsing as LoginResponse
                if (token == null || token.isEmpty()) {
                    try {
                        LoginResponse loginResponse = JsonUtils.fromJson(response.getBody().asString(), LoginResponse.class);
                        token = loginResponse.getAuthToken();
                    } catch (Exception e) {
                        // Ignore parsing errors
                    }
                }
                
                if (token != null && !token.isEmpty()) {
                    return token;
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to authenticate for role " + role + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        // Fallback to config token
        String configKey = "auth.token." + role.name().toLowerCase();
        String token = configManager.getProperty(configKey);
        return token != null && !token.isEmpty() ? token : configManager.getAuthToken();
    }
    
    /**
     * Get token by calling login API with provided credentials
     * @param email User email
     * @param password User password
     * @return Authentication token
     */
    public String getToken(String email, String password) {
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
        
        if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
            // Try multiple JSON paths to extract token
            String token = response.jsonPath().getString("data.attributes.token");
            if (token == null || token.isEmpty()) {
                token = response.jsonPath().getString("data.token");
            }
            if (token == null || token.isEmpty()) {
                token = response.jsonPath().getString("data.accessToken");
            }
            if (token == null || token.isEmpty()) {
                token = response.jsonPath().getString("token");
            }
            if (token == null || token.isEmpty()) {
                token = response.jsonPath().getString("accessToken");
            }
            
            if (token != null && !token.isEmpty()) {
                return token;
            }
            
            // Try parsing as LoginResponse
            try {
                LoginResponse loginResponse = JsonUtils.fromJson(response.getBody().asString(), LoginResponse.class);
                token = loginResponse.getAuthToken();
                if (token != null && !token.isEmpty()) {
                    return token;
                }
            } catch (Exception e) {
                // Ignore parsing errors
            }
        }
        
        throw new RuntimeException("Failed to get token. Status: " + response.getStatusCode() + 
                                   ", Response: " + response.getBody().asString());
    }

    /**
     * Clear cached tokens (useful for logout scenarios)
     */
    public void clearTokens() {
        tokens.clear();
    }

    /**
     * Clear token for a specific role
     * @param role User role
     */
    public void clearToken(Role role) {
        tokens.remove(role.name());
    }

    /**
     * Set token for a role (useful for testing with pre-generated tokens)
     * @param role User role
     * @param token Authentication token
     */
    public void setToken(Role role, String token) {
        tokens.put(role.name(), token);
    }
}
