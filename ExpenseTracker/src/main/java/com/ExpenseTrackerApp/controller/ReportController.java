package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.ReportRequest;
import com.ExpenseTrackerApp.dto.Response.ReportResponse;
import com.ExpenseTrackerApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public ResponseEntity<ReportResponse> generateReport(@RequestBody ReportRequest request) {
        ReportResponse response = reportService.generateReport(request);
        return ResponseEntity.ok(response);
    }
}