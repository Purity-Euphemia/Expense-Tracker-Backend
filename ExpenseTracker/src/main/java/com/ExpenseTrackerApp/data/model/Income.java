package com.ExpenseTrackerApp.data.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Income {
    private int id;
    private double amount;
    private String source;
    private LocalDate date;
}
