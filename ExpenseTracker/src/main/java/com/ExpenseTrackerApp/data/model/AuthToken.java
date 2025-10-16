package com.ExpenseTrackerApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "auth_tokens")
public class AuthToken {
    @Id
    private String token;
    private String userEmail;
}
