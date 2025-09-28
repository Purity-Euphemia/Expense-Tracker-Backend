package com.ExpenseTrackerApp.utils;

import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;

public class ValidationUtils {
    public static void validateUser(RegisterUserRequest registerUserRequest) {
        if(registerUserRequest.getName() == null || registerUserRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
