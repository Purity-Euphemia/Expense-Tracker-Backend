package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "budgets")
public class Budget {
    @Id
    private String id;
    private String userEmail;
    private String categoryId;
    private double amount;
    private int month;
    private int year;


}
