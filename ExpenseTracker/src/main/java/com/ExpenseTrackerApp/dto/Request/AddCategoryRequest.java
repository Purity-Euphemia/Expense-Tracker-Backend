package com.ExpenseTrackerApp.dto.Request;

import lombok.Data;

@Data
public class AddCategoryRequest {
    private String name;
    private String type;
}
