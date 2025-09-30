package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Budget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetRepository {
    private final List<Budget> budgets = new ArrayList<>();

    public Budget save(Budget budget) {
        if (budget.getId() == 0) {
            budget.setId(budgets.size() + 1);
            budgets.add(budget);
        } else {
            deleteById(budget.getId());
            budgets.add(budget);
        }
        return budget;
    }

    public Budget findById(int id) {
        return budgets.stream().filter(budget -> budget.getId() == id).findFirst().orElse(null);
    }
    public List<Budget> findAll() {
        return new ArrayList<>(budgets);
    }
    public List<Budget> findByUserId(int userId) {
        return budgets.stream().filter(budget -> budget.getUserId() == userId).collect(Collectors.toList());
    }
    public void deleteById(int id) {
        budgets.removeIf(budget -> budget.getId() == id);
    }
    public Budget findByUserCategoryMonthYear(int userId, int categoryId, int month, int year) {
        return budgets.stream().filter(budget -> budget.getUserId() == userId && budget.getCategoryId() == categoryId && budget.getMonth() == month && budget.getYear() == year).findFirst().orElse(null);
    }
}
