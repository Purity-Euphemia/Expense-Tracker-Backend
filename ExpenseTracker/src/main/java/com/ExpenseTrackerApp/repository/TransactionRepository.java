package com.ExpenseTrackerApp.repository;

import com.ExpenseTrackerApp.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUsername(String username);
}
