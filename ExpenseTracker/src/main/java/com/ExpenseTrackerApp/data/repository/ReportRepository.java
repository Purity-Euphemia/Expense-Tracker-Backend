package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.model.Income;

import java.time.LocalDate;
import java.util.List;

public class ReportRepository {
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public ReportRepository(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }
    public List<Expense> getExpensesInRange(int userId, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByUserAndDateRange(userId, startDate, endDate);
    }
    public List<Income> getIncomesInRange(int userId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.findByUserAndDateRange(userId,startDate, endDate);
    }
}
