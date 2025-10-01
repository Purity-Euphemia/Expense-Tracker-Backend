package com.ExpenseTrackerApp.data.model;

import lombok.Data;

@Data
public class AuthToken {
    private String token;
    private int userId;
}
