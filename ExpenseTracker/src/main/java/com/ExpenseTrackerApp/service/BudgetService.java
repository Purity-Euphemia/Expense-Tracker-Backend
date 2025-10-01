package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Budget;
import com.ExpenseTrackerApp.data.repository.BudgetRepository;
import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class BudgetService {
    private final BudgetRepository budgetRepository = new BudgetRepository();

    public BudgetResponse setBudget(AddBudgetRequest addBudgetRequest) {
        ValidationUtils.validateBudget(addBudgetRequest);

        Budget budget = new Budget();
        budget.setUserId(addBudgetRequest.getUserId());
        budget.setCategoryId(addBudgetRequest.getCategoryId());
        budget.setAmount(addBudgetRequest.getAmount());
        budget.setMonth(addBudgetRequest.getMonth());
        budget.setYear(addBudgetRequest.getYear());

        Budget saved = budgetRepository.save(budget);
        return toResponse(saved);
    }
    public List<BudgetResponse> getUserBudgets(int userId) {
        List<Budget> all = budgetRepository.findByUserId(userId);
        List<BudgetResponse> respList = new ArrayList<>();
        for (Budget budget : all) {
            respList.add(toResponse(budget));
        }
        return respList;
    }
    public BudgetResponse updateBudget(int id, UpdateBudgetRequest updateBudgetRequest) {
        Budget existing = budgetRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Budget not found with id " + id);
        }
        if (updateBudgetRequest.getAmount() != null) {
            existing.setAmount(updateBudgetRequest.getAmount());
        }
        if (updateBudgetRequest.getMonth() != null) {
            existing.setMonth(updateBudgetRequest.getMonth());
        }
        if (updateBudgetRequest.getYear() != null) {
            existing.setYear(updateBudgetRequest.getYear());
        }
        Budget saved = budgetRepository.save(existing);
        return toResponse(saved);
    }
    public String deleteBudget(int id) {
        Budget existing = budgetRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Budget not found with id " + id);
        }
        budgetRepository.deleteById(id);
        return "Budget deleted";
    }
    public BudgetResponse getBudgetForUserCategoryMonth(int userId, int categoryId, int month, int year) {
        Budget budget = budgetRepository.findByUserCategoryMonthYear(userId, categoryId, month, year);
        if (budget == null) {
            throw new ResourceNotFoundException("No budget found");
        }
        return toResponse(budget);
    }
    private BudgetResponse toResponse(Budget b) {
        BudgetResponse budgetResponse = new BudgetResponse();
        budgetResponse.setId(b.getId());
        budgetResponse.setUserId(b.getUserId());
        budgetResponse.setCategoryId(b.getCategoryId());
        budgetResponse.setAmount(b.getAmount());
        budgetResponse.setMonth(b.getMonth());
        budgetResponse.setYear(b.getYear());
        return budgetResponse;
    }
}
