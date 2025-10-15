package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddExpenseResponse {
    private String id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
    private String message;
}
