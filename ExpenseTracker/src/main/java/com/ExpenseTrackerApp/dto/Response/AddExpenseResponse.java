package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddExpenseResponse {
    private int id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
}
