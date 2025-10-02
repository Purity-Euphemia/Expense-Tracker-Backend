package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.AddCategoryRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateCategoryRequest;
import com.ExpenseTrackerApp.dto.Response.CategoryResponse;
import com.ExpenseTrackerApp.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        return categoryService.addCategory(addCategoryRequest);
    }
    @GetMapping
    public CategoryResponse getAllCategories() {
        return categoryService.getAllCategories().get(0);
    }
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable("id") String id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.updateCategory(id, updateCategoryRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        return categoryService.deleteCategory(id);
    }
}
