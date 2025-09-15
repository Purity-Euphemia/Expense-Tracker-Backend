package com.ExpenseTrackerApp.repository;

import com.ExpenseTrackerApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
