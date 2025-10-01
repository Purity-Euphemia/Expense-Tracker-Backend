package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

import java.util.Map;

@Data
public class ReportResponse {
    private String userId;
    private Map<String, Double> expenseByCategory;
    private double totalIncome;
    private double totalExpense;
}
