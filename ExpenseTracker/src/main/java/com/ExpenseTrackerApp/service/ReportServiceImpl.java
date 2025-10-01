package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.model.Income;
import com.ExpenseTrackerApp.data.repository.ReportRepository;
import com.ExpenseTrackerApp.dto.Request.ReportRequest;
import com.ExpenseTrackerApp.dto.Response.ReportResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    public ReportResponse generateReport(ReportRequest req) {
        List<Expense> expenses = reportRepository.getExpensesInRange(req.getUserId(), req.getStartDate(), req.getEndDate());
        List<Income> incomes = reportRepository.getIncomeInRange(req.getUserId(), req.getStartDate(), req.getEndDate());

        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();

        Map<String, Double> byCategory = new HashMap<>();
        for (Expense expense : expenses) {
            byCategory.put(
                    expense.getCategory(),
                    byCategory.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount()
            );
        }
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setUserId(req.getUserId());
        reportResponse.setExpenseByCategory(byCategory);
        reportResponse.setTotalExpense(totalExpense);
        reportResponse.setTotalIncome(totalIncome);
        return reportResponse;
    }
}
