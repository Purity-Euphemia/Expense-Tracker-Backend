package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddExpenseResponse {
    private String id;
    private String userId;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
}
