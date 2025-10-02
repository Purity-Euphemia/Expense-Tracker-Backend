package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.Category;
import com.ExpenseTrackerApp.data.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategoryTest() {
        categoryRepository.deleteAll();
        assertEquals(0, categoryRepository.count());

        Category category = new Category();
        category.setName("Food");
        category.setType("expense");
        categoryRepository.save(category);

        assertEquals(1, categoryRepository.count());
    }

}
