package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "incomes")
public class Income {
    @Id
    private String id;
    private String userEmail;
    private double amount;
    private String source;
    private LocalDate date;
}
