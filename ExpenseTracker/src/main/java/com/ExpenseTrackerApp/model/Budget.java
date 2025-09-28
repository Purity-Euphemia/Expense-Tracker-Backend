package com.ExpenseTrackerApp.model;

import lombok.Data;

@Data
public class Budget {
    private int id;
    private int userId;
    private int categoryId;
    private double amount;
    private int month;
    private int year;
}
