package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    Optional<Budget> findByUserIdAndCategoryAndMonthAndYear(String userId, String category, int month, int year);
    List<Budget> findByUserId(String userId);
}

