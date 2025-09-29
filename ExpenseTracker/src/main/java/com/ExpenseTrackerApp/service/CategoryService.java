package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Category;
import com.ExpenseTrackerApp.data.repository.CategoryRepository;
import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
import com.ExpenseTrackerApp.dto.Response.CategoryResponse;
import com.ExpenseTrackerApp.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        ValidationUtils.validateCategory(addCategoryRequest);

        Category category = new Category();
        category.setName(addCategoryRequest.getName());
        category.setType(addCategoryRequest.getType().toUpperCase());

        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(toResponse(category));
        }
        return responses;
    }
    private CategoryResponse toResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setType(category.getType());
        return response;
    }
}
