package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.data.repository.ExpenseRepository;
import com.ExpenseTrackerApp.data.repository.ReportRepository;
import com.ExpenseTrackerApp.dto.Request.ReportRequest;
import com.ExpenseTrackerApp.dto.Response.ReportResponse;
import com.ExpenseTrackerApp.service.ReportServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportServiceImpl service;

    public ReportController() {

        ReportRepository reportRepository = new ReportRepository(new ExpenseRepository(), new IncomeRepository());
        this.service = new ReportServiceImpl(reportRepository);
    }

    @PostMapping
    public ReportResponse generate(@RequestBody ReportRequest reportRequest) {
        return service.generateReport(reportRequest);
    }
}
}
