package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;

import java.util.List;

public interface BudgetService {
    BudgetResponse setBudget(AddBudgetRequest addBudgetRequest);
    List<BudgetResponse> getBudgetsByUser(String userId);
    BudgetResponse updateBudget(String id, AddBudgetRequest addBudgetRequest);
    String deleteBudget(String id);
}
