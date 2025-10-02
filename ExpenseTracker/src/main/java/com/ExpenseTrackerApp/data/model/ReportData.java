package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Data
@Document(collection = "report_data")
public class ReportData {
    @Id
    private String id;
    private String userId;
    private Map<String, Double> totalsByCategory;
    private Map<String, Double> incomeBySource;
    private double totalIncome;
    private double totalExpense;
    private double balance;
    private int month;
    private int year;
    private String title;
    private LocalDate dateGenerated;
}
