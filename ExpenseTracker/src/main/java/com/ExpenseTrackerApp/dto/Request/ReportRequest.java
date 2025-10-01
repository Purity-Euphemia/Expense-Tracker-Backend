package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportRequest {
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
