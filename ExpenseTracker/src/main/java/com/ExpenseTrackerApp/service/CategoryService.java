package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateCategoryRequest;
import com.ExpenseTrackerApp.dto.Response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(String id);
    CategoryResponse updateCategory(String id, UpdateCategoryRequest updateCategoryRequest);
    String deleteCategory(String id);
}
