package com.ExpenseTrackerApp.utils;

import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
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

    public static void validateCategory(AddCategoryRequest addCategoryRequest) {
        if (addCategoryRequest.getName() == null || addCategoryRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required.");
        }
        if (addCategoryRequest.getType() == null || addCategoryRequest.getType().isBlank()) {
            throw new IllegalArgumentException("Category type is required.");
        }
        String type = addCategoryRequest.getType().toLowerCase();
        if (!(type.equals("income") || type.equals("expense"))) {
            throw new IllegalArgumentException("Category type must be 'income' or 'expense'.");
        }
    }

    public static void validateBudget(AddBudgetRequest addBudgetRequest) {
        if (addBudgetRequest.getUserId() <= 0) {
            throw new IllegalArgumentException("UserId must be positive");
        }
        if (addBudgetRequest.getCategoryId() <= 0) {
            throw new IllegalArgumentException("CategoryId must be positive");
        }
        if (addBudgetRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (addBudgetRequest.getMonth() < 1 || addBudgetRequest.getMonth() > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        if (addBudgetRequest.getYear() < 2000) {
            throw new IllegalArgumentException("Year must be valid");
        }
    }


}
