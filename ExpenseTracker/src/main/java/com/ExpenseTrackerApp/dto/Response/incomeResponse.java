package com.ExpenseTrackerApp.dto.Response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeResponse {
    private String id;
    private String userId;
    private double amount;
    private String source;
    private LocalDate date;


}
