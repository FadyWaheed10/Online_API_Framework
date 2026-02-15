package com.foodics.api.pom;

import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

/**
 * Authentication Page Object Model - V1
 * Encapsulates all authentication-related API endpoints
 */
public class AuthPage extends ApiClient {

    private static final String LOGIN_ENDPOINT = "/login";
    private static final String GUEST_LOGIN_ENDPOINT = "/login/guest";
    private static final String LOGOUT_ENDPOINT = "/logout";
    private static final String RESET_PASSWORD_ENDPOINT = "/forgot-password";
    private static final String RESEND_OTP_ENDPOINT = "/resendsms";

    /**
     * Login as Employee
     * @param credentials Login credentials (username/email and password)
     * @return API response with authentication token
     */
    public Response login(Object credentials) {
        return withoutAuth()
                .withBody(credentials)
                .post(LOGIN_ENDPOINT);
    }

    /**
     * Guest Login
     * @param credentials Guest login credentials (username and password)
     * @return API response
     */
    public Response guestLogin(Object credentials) {
        return withoutAuth()
                .withBody(credentials)
                .post(GUEST_LOGIN_ENDPOINT);
    }

    /**
     * Logout
     * @param token Authentication token
     * @return API response
     */
    public Response logout(String token) {
        return withAuth(token)
                .post(LOGOUT_ENDPOINT);
    }

    /**
     * Reset Password
     * @param resetData Password reset data
     * @return API response
     */
    public Response resetPassword(Object resetData) {
        return withoutAuth()
                .withBody(resetData)
                .post(RESET_PASSWORD_ENDPOINT);
    }

    /**
     * Resend OTP
     * @param otpData OTP request data
     * @return API response
     */
    public Response resendOTP(Object otpData) {
        return withoutAuth()
                .withBody(otpData)
                .post(RESEND_OTP_ENDPOINT);
    }
}
