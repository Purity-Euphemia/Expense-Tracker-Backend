package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.ReportData;
import com.ExpenseTrackerApp.data.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void saveReportTest() {
        reportRepository.deleteAll();

        ReportData report = new ReportData();
        report.setUserId("user123");
        report.setTitle("October Report");
        report.setTotalIncome(5000.0);
        report.setTotalExpense(3000.0);
        report.setDateGenerated(LocalDate.of(2025, 10, 2));

        reportRepository.save(report);

        assertEquals(1, reportRepository.count());
    }
}
