package com.foodics.api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

/**
 * Response Validator utility class
 * Provides common validation methods for API responses
 */
public class ResponseValidator {

    /**
     * Validate status code
     * @param response API response
     * @param expectedStatusCode Expected HTTP status code
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code: " + expectedStatusCode + " but got: " + response.getStatusCode());
    }

    /**
     * Validate response contains a field
     * @param response API response
     * @param fieldPath JSON path to the field
     */
    public static void validateFieldExists(Response response, String fieldPath) {
        Assert.assertNotNull(response.jsonPath().get(fieldPath),
                "Field " + fieldPath + " does not exist in response");
    }

    /**
     * Validate response field value
     * @param response API response
     * @param fieldPath JSON path to the field
     * @param expectedValue Expected value
     */
    public static void validateFieldValue(Response response, String fieldPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(fieldPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Field " + fieldPath + " value mismatch. Expected: " + expectedValue + ", Actual: " + actualValue);
    }

    /**
     * Validate response time is within limit
     * @param response API response
     * @param maxTimeInMs Maximum allowed time in milliseconds
     */
    public static void validateResponseTime(Response response, long maxTimeInMs) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime <= maxTimeInMs,
                "Response time " + actualTime + "ms exceeds maximum " + maxTimeInMs + "ms");
    }

    /**
     * Validate response content type
     * @param response API response
     * @param expectedContentType Expected content type
     */
    public static void validateContentType(Response response, String expectedContentType) {
        String actualContentType = response.getContentType();
        Assert.assertTrue(actualContentType.contains(expectedContentType),
                "Content type mismatch. Expected: " + expectedContentType + ", Actual: " + actualContentType);
    }

    /**
     * Validate response header
     * @param response API response
     * @param headerName Header name
     * @param expectedValue Expected header value
     */
    public static void validateHeader(Response response, String headerName, String expectedValue) {
        String actualValue = response.getHeader(headerName);
        Assert.assertEquals(actualValue, expectedValue,
                "Header " + headerName + " value mismatch. Expected: " + expectedValue + ", Actual: " + actualValue);
    }
}
