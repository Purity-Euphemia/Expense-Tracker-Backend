package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int userId;
}
