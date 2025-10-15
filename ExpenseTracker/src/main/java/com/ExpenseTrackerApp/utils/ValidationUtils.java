package com.ExpenseTrackerApp.utils;

import com.ExpenseTrackerApp.dto.Request.*;
import com.ExpenseTrackerApp.exception.BadRequestException;

public class ValidationUtils {

    public static void validateUser(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest.getName() == null || registerUserRequest.getName().isBlank()) {
            throw new BadRequestException("Name is required");
        }
        if (registerUserRequest.getEmail() == null || registerUserRequest.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (registerUserRequest.getPassword() == null || registerUserRequest.getPassword().isBlank()) {
            throw new BadRequestException("Password is required");
        }
        if (registerUserRequest.getPassword().length() < 4) {
            throw new BadRequestException("Password must be at least 4 characters long");
        }
        if (!registerUserRequest.getEmail().contains("@")) {
            throw new BadRequestException("Invalid email format");
        }
    }

    public static void validateExpense(AddExpenseRequest addExpenseRequest) {
        if (addExpenseRequest.getUserId() == null || addExpenseRequest.getUserId().isBlank()) {
            throw new BadRequestException("User id is required");
        }
        if (addExpenseRequest.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }
        if (addExpenseRequest.getCategory() == null || addExpenseRequest.getCategory().isBlank()) {
            throw new BadRequestException("Category is required");
        }
        if (addExpenseRequest.getDescription() == null || addExpenseRequest.getDescription().isBlank()) {
            throw new BadRequestException("Description is required");
        }
        if (addExpenseRequest.getDate() == null) {
            throw new BadRequestException("Date is required");
        }
    }

    public static void validateCategory(AddCategoryRequest addCategoryRequest) {
        if (addCategoryRequest.getName() == null || addCategoryRequest.getName().isBlank()) {
            throw new BadRequestException("Category name is required.");
        }
        if (addCategoryRequest.getType() == null || addCategoryRequest.getType().isBlank()) {
            throw new BadRequestException("Category type is required.");
        }
        String type = addCategoryRequest.getType().toLowerCase();
        if (!(type.equals("income") || type.equals("expense"))) {
            throw new BadRequestException("Category type must be 'income' or 'expense'.");
        }
    }

    public static void validateBudget(AddBudgetRequest addBudgetRequest) {
        if (addBudgetRequest.getUserId() == null || addBudgetRequest.getUserId().isBlank()) {
            throw new BadRequestException("UserId is required");
        }
        if (addBudgetRequest.getCategoryId() == null || addBudgetRequest.getCategoryId().isBlank()) {
            throw new BadRequestException("CategoryId is required");
        }
        if (addBudgetRequest.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }
        if (addBudgetRequest.getMonth() < 1 || addBudgetRequest.getMonth() > 12) {
            throw new BadRequestException("Month must be between 1 and 12");
        }
        if (addBudgetRequest.getYear() < 2000) {
            throw new BadRequestException("Year must be valid");
        }
    }

    public static void validateIncome(AddIncomeRequest addIncomeRequest) {
        if (addIncomeRequest.getUserId() == null || addIncomeRequest.getUserId().isBlank()) {
            throw new BadRequestException("UserId is required.");
        }
        if (addIncomeRequest.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than zero.");
        }
        if (addIncomeRequest.getSource() == null || addIncomeRequest.getSource().isBlank()) {
            throw new BadRequestException("Source is required.");
        }
        if (addIncomeRequest.getDate() == null) {
            throw new BadRequestException("Date is required.");
        }
    }
}
