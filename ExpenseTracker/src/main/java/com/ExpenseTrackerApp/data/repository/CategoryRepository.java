package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final List<Category> categories = new ArrayList<>();


    public Category save(Category category) {
        if (category.getId() == 0) {
            category.setId(categories.size() + 1);
            categories.add(category);
        } else {
            deleteById(category.getId());
            categories.add(category);
        }
        return category;
    }
    public Category findById(int id) {
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }
    public List<Category> findAll() {
        return new ArrayList<>(categories);
    }
    public void deleteById(int id) {
        categories.removeIf(category -> category.getId() == id);
    }
}
