package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class AddBudgetRequest {
    private String userId;
    private String categoryId;
    private double amount;
    private int month;
    private int year;
}
