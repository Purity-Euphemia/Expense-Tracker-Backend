package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
import com.ExpenseTrackerApp.dto.Response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse updateCategory(String id, AddCategoryRequest addCategoryRequest);
    String deleteCategory(String id);
}
