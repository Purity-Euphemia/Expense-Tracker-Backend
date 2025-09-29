package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
    private String name;
    private String type;
}
