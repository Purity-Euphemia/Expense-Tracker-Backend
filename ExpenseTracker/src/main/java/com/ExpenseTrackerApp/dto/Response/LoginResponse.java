package com.ExpenseTrackerApp.dto.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userEmail;
    private String email;
    private String message;
}
