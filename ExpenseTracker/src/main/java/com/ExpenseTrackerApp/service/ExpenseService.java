package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddExpenseRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateExpenseRequest;
import com.ExpenseTrackerApp.dto.Response.AddExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    AddExpenseResponse addExpense(AddExpenseRequest addExpenseResqeust);
    List<AddExpenseResponse> getExpensesByUser(String userId);
    AddExpenseResponse updateExpense(String id, UpdateExpenseRequest updateExpenseRequest);
    String deleteExpense(String id);
    double getMonthlyTotal(String userId, int month, int year);
    List<AddExpenseResponse> getExpensesInRange(String userId, LocalDate startDate, LocalDate endDate);
}
