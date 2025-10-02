package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Budget;
import com.ExpenseTrackerApp.data.repository.BudgetRepository;
import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
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
    @Override
    public List<BudgetResponse> getUserBudgets(String userId) {
        List<Budget> all = budgetRepository.findByUserId(userId);
        List<BudgetResponse> respList = new ArrayList<>();
        for (Budget budget : all) {
            respList.add(toResponse(budget));
        }
        return respList;
    }
    @Override
    public BudgetResponse updateBudget(String id, UpdateBudgetRequest request) {
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + id)
        );
        budget.setAmount(request.getAmount());
        return toResponse(budgetRepository.save(budget));
    }
    @Override
    public String deleteBudget(String id) {
        Budget existing = budgetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Budget not found with id " + id));
        budgetRepository.deleteById(id);
        return "Budget deleted";
    }
    @Override
    public BudgetResponse getBudgetForUserCategoryMonth(String userId, String categoryId, int month, int year) {
        Budget budget = budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(userId, categoryId, month, year).orElseThrow(() -> new ResourceNotFoundException("No budget found"));
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
