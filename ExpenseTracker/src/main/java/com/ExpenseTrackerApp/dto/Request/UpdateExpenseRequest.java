package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateExpenseRequest {
    private Double amount;
    private String category;
    private String description;
    private LocalDate date;
}
