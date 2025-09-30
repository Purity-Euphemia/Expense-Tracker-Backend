package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Budget;
import com.ExpenseTrackerApp.data.repository.BudgetRepository;
import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BudgetService {
    private final BudgetRepository repo = new BudgetRepository();

    public BudgetResponse setBudget(AddBudgetRequest req) {
        ValidationUtils.validateBudget(req);

        Budget budget = new Budget();
        budget.setUserId(req.getUserId());
        budget.setCategoryId(req.getCategoryId());
        budget.setAmount(req.getAmount());
        budget.setMonth(req.getMonth());
        budget.setYear(req.getYear());

        Budget saved = repo.save(budget);
        return toResponse(saved);
    }
    public List<BudgetResponse> getUserBudgets(int userId) {
        List<Budget> all = repo.findByUserId(userId);
        List<BudgetResponse> respList = new ArrayList<>();
        for (Budget b : all) {
            respList.add(toResponse(b));
        }
        return respList;
    }
    public BudgetResponse updateBudget(int id, UpdateBudgetRequest updateBudgetRequest) {
        Budget existing = repo.findById(id);
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
        Budget saved = repo.save(existing);
        return toResponse(saved);
    }
    public String deleteBudget(int id) {
        Budget existing = repo.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Budget not found with id " + id);
        }
        repo.deleteById(id);
        return "Budget deleted";
    }
    public BudgetResponse getBudgetForUserCategoryMonth(int userId, int categoryId, int month, int year) {
        Budget b = repo.findByUserCategoryMonthYear(userId, categoryId, month, year);
        if (b == null) {
            throw new ResourceNotFoundException("No budget found");
        }
        return toResponse(b);
    }
    private BudgetResponse toResponse(Budget b) {
        BudgetResponse resp = new BudgetResponse();
        resp.setId(b.getId());
        resp.setUserId(b.getUserId());
        resp.setCategoryId(b.getCategoryId());
        resp.setAmount(b.getAmount());
        resp.setMonth(b.getMonth());
        resp.setYear(b.getYear());
        return resp;
    }
}
