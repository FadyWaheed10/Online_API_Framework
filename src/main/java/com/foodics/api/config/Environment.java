package com.foodics.api.config;

/**
 * Enum for different environments
 */
public enum Environment {
    QA("qa"),
    STAGING("staging"),
    PRODUCTION("production");

    private final String value;

    Environment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
