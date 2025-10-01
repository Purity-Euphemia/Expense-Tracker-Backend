package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CategoryRepository extends MongoRepository<Category, String> {

}
