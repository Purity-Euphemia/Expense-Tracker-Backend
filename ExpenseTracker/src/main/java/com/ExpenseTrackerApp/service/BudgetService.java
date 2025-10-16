package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;

import java.util.List;

public interface BudgetService {
    BudgetResponse setBudget(AddBudgetRequest addBudgetRequest);
    List<BudgetResponse> getUserBudgets(String userEmail);
    BudgetResponse updateBudget(String id, UpdateBudgetRequest updateBudgetRequest);
    String deleteBudget(String id);
    BudgetResponse getBudgetForUserCategoryMonth(String userEmail, String categoryId, int month, int year);
}
