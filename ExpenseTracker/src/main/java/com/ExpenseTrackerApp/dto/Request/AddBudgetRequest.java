package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class AddBudgetRequest {
    private int userId;
    private int categoryId;
    private double amount;
    private int month;
    private int year;
}
