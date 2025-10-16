package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Income;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends MongoRepository<Income, String> {
    List<Income> findByUserId(String userEmail);
    List<Income> findByUserIdAndDateBetween(String userEmail, LocalDate start, LocalDate end);
}
