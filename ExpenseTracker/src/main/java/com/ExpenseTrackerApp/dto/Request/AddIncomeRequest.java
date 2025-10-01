package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddIncomeRequest {
    private String userId;
    private double amount;
    private String source;
    private LocalDate date;
}
