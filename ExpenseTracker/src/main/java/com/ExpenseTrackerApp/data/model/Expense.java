package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "transactions")
public class Expense {
    @Id
    private String id;
    private int userId;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
}
