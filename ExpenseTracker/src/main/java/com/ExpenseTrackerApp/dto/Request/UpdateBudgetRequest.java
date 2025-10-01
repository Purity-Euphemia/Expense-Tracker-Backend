package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class UpdateBudgetRequest {
    private Double amount;
    private int month;
    private int year;
}
