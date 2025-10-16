package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

@Data
public class BudgetResponse {
    private String id;
    private String  userEmail;
    private String categoryId;
    private double amount;
    private int month;
    private int year;
    private String message;
}
