package com.ExpenseTrackerApp.dto.Response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeResponse {
    private int id;
    private int userId;
    private double amount;
    private String source;
    private LocalDate date;


}
