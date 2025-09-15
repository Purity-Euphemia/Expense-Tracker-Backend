package com.ExpenseTrackerApp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private String paymentMethod;
    private double amount;
    private String date;
    private String category;
    private String type;
    private String notes;
    private String username;
    private boolean recurring;
}
