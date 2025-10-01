package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<AuthToken, String> {
    Optional<AuthToken> findByToken(String token);

}
