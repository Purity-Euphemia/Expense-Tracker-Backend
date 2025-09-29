package com.ExpenseTrackerApp.utils;

import com.ExpenseTrackerApp.dto.Request.AddExpenseRequest;
import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;

public class ValidationUtils {
    public static void validateUser(RegisterUserRequest registerUserRequest) {
        if(registerUserRequest.getName() == null || registerUserRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if(registerUserRequest.getEmail() == null || registerUserRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if(registerUserRequest.getPassword() == null || registerUserRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if(registerUserRequest.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        if(!registerUserRequest.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

    }

    public static void validateExpense(AddExpenseRequest addExpenseRequest) {
        if (addExpenseRequest.getUserId() <= 0) {
            throw new IllegalArgumentException("User id must be positive");
        }
        if (addExpenseRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (addExpenseRequest.getCategory() == null || addExpenseRequest.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
        if (addExpenseRequest.getDescription() == null || addExpenseRequest.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (addExpenseRequest.getDate() == null) {
            throw new IllegalArgumentException("Date is required");
        }
    }
}
