package com.ExpenseTrackerApp.data.model;

import lombok.Data;

import java.util.Map;

@Data
public class ReportData {
    private int userId;
    private Map<String, Double> totalsByCategory;
    private double totalIncome;
    private double totalExpense;
}
