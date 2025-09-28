package com.ExpenseTrackerApp.dto.Response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class incomeResponse {
    private int id;
    private double amount;
    private String source;
    private LocalDate date;


}
