package com.foodics.api.auth;

/**
 * User roles enum
 * Based on API response - roles array contains role names like "administrator"
 */
public enum Role {
    ADMINISTRATOR("administrator"),
    ADMIN("admin"),
    USER("user"),
    EMPLOYEE("employee"),
    MERCHANT("merchant"),
    CUSTOMER("customer"),
    GUEST("guest");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
