package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

@Data
public class BudgetResponse {
    private int id;
    private int userId;
    private int categoryId;
    private double amount;
    private int month;
    private int year;
}
