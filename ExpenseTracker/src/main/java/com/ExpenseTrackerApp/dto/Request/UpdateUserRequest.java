package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
