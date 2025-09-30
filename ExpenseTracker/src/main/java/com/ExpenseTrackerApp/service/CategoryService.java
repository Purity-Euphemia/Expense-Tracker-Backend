package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Category;
import com.ExpenseTrackerApp.data.repository.CategoryRepository;
import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateCategoryRequest;
import com.ExpenseTrackerApp.dto.Response.CategoryResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
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
    public CategoryResponse getCategoryById(int id) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new IllegalArgumentException("Category not found with id " + id);
        }
        return toResponse(category);
    }
    public CategoryResponse updateCategory(int id, UpdateCategoryRequest updateCategoryRequest) {
        Category existing = categoryRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
        if (updateCategoryRequest.getName() != null && !updateCategoryRequest.getName().isBlank()) {
            existing.setName(updateCategoryRequest.getName());
        }
        if (updateCategoryRequest.getType() != null && !updateCategoryRequest.getType().isBlank()) {
            existing.setType(updateCategoryRequest.getType().toUpperCase());
        }
        Category saved = categoryRepository.save(existing);
        return toResponse(saved);
    }
    public String deleteCategory(int id) {
        Category existing = categoryRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
