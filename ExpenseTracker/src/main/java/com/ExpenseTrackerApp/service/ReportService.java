package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.ReportRequest;
import com.ExpenseTrackerApp.dto.Response.ReportResponse;

public interface ReportService {
    ReportResponse generateReport(ReportRequest req);
    //ReportResponse generateMonthlyReport(String userId, int month, int year);
}
