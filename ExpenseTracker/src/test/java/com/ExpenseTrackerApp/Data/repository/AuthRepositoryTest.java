package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.AuthToken;
import com.ExpenseTrackerApp.data.repository.AuthRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class AuthRepositoryTest {

    @Autowired
    private AuthRepository authRepository;

    @Test
    public void saveAuthTokenTest() {
        authRepository.deleteAll();
        authRepository.save(new AuthToken());
        assertEquals(1, authRepository.count());
    }
}
