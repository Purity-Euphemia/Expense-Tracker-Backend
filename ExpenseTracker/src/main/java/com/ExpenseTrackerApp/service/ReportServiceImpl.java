package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.model.Income;
import com.ExpenseTrackerApp.data.repository.ExpenseRepository;
import com.ExpenseTrackerApp.data.repository.IncomeRepository;
import com.ExpenseTrackerApp.dto.Request.ReportRequest;
import com.ExpenseTrackerApp.dto.Response.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public ReportResponse generateReport(ReportRequest req) {
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(
                req.getUserId(), req.getStartDate(), req.getEndDate());

        List<Income> incomes = incomeRepository.findByUserIdAndDateBetween(
                req.getUserId(), req.getStartDate(), req.getEndDate());

        double totalExpense = 0.0;
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }

        double totalIncome = 0.0;
        for (Income income : incomes) {
            totalIncome += income.getAmount();
        }

        Map<String, Double> byCategory = new HashMap<>();
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double currentAmount = byCategory.getOrDefault(category, 0.0);
            byCategory.put(category, currentAmount + expense.getAmount());
        }

        ReportResponse response = new ReportResponse();
        response.setUserId(req.getUserId());
        response.setExpenseByCategory(byCategory);
        response.setTotalExpense(totalExpense);
        response.setTotalIncome(totalIncome);

        return response;
    }
}
