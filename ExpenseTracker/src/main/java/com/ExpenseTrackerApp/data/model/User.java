package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
}
