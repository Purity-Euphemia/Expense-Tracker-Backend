package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;
}
