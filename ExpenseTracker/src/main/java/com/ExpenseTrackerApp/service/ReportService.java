package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Response.ReportResponse;

public interface ReportService {
    ReportResponse generateMonthlyReport(String userId, int month, int year);
}
