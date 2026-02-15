package com.foodics.api.utils;

import com.foodics.api.config.ConfigManager;
import com.foodics.api.pom.AuthPage;
import com.foodics.api.models.LoginRequest;
import io.restassured.response.Response;

/**
 * Helper utility to manage concept keys
 * Extracts and caches concept keys from login responses
 */
public class ConceptKeyHelper {
    private static String cachedConceptKey;
    private static ConfigManager configManager = ConfigManager.getInstance();

    /**
     * Get concept key from login response or cache
     * @param token Authentication token (optional, for extracting from login)
     * @return Concept key
     */
    public static String getConceptKey(String token) {
        if (cachedConceptKey != null && !cachedConceptKey.isEmpty()) {
            return cachedConceptKey;
        }

        // Try to get from config first
        String conceptKey = configManager.getProperty("concept.key");
        if (conceptKey != null && !conceptKey.isEmpty()) {
            cachedConceptKey = conceptKey;
            return conceptKey;
        }

        // If token provided, try to extract from login response
        if (token != null) {
            try {
                String email = configManager.getProperty("test.user.email");
                String password = configManager.getProperty("test.user.password");
                
                if (email != null && password != null) {
                    AuthPage authPage = new AuthPage();
                    LoginRequest loginRequest = new LoginRequest();
                    if (email != null && !email.contains("@")) {
                        loginRequest.setUsername(email);
                    } else {
                        loginRequest.setEmail(email);
                    }
                    loginRequest.setPassword(password);
                    
                    Response loginResponse = authPage.login(loginRequest);
                    conceptKey = loginResponse.jsonPath().getString("data.attributes.concepts[0].key");
                    
                    if (conceptKey != null && !conceptKey.isEmpty()) {
                        cachedConceptKey = conceptKey;
                        return conceptKey;
                    }
                }
            } catch (Exception e) {
                // Ignore errors
            }
        }

        // Default fallback
        return "1";
    }

    /**
     * Set concept key manually
     * @param conceptKey Concept key to cache
     */
    public static void setConceptKey(String conceptKey) {
        cachedConceptKey = conceptKey;
    }

    /**
     * Clear cached concept key
     */
    public static void clearConceptKey() {
        cachedConceptKey = null;
    }
}
