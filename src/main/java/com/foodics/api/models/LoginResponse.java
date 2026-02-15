package com.foodics.api.models;

/**
 * Login Response Model
 * Update field names based on your actual API response structure
 */
public class LoginResponse {
    private String token;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
    private Object data;
    private String message;
    private String status;
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getRefreshToken() {
        return refreshToken;
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }
    
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    public Long getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Get the token from response
     * Handles different response structures
     * @return Authentication token
     */
    public String getAuthToken() {
        if (token != null && !token.isEmpty()) {
            return token;
        }
        if (accessToken != null && !accessToken.isEmpty()) {
            return accessToken;
        }
        if (data != null && data instanceof java.util.Map) {
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> dataMap = (java.util.Map<String, Object>) data;
            if (dataMap.containsKey("token")) {
                return String.valueOf(dataMap.get("token"));
            }
            if (dataMap.containsKey("accessToken")) {
                return String.valueOf(dataMap.get("accessToken"));
            }
        }
        return null;
    }
}
