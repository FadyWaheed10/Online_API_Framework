package com.foodics.api.pom.v1;

import com.foodics.api.auth.Role;
import com.foodics.api.base.ApiClient;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Employee Page Object Model - V1
 * Encapsulates all employee-related API endpoints
 */
public class EmployeePage extends ApiClient {

    private static final String EMPLOYEE_FORGOT_PASSWORD_ENDPOINT = "/forgot-password/employee";
    private static final String EMPLOYEE_BY_ID_ENDPOINT = "/employees/{id}";

    public Response forgotPassword(Object passwordData) {
        return withoutAuth()
                .withBody(passwordData)
                .post(EMPLOYEE_FORGOT_PASSWORD_ENDPOINT);
    }

    public Response updateEmployee(String employeeId, Object employeeData, String conceptKey) {
        Map<String, Object> pathParams = Map.of("id", employeeId);
        return withAuth(Role.USER)
                .withConceptKey(conceptKey)
                .withPathParams(pathParams)
                .withBody(employeeData)
                .patch(EMPLOYEE_BY_ID_ENDPOINT);
    }
}
