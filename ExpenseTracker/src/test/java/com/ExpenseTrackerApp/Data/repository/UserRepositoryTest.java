package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.User;
import com.ExpenseTrackerApp.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("password123");

        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void findByEmailTest() {
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setEmail("findme@example.com");
        user.setName("Find Me");
        user.setPassword("password456");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("findme@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("Find Me", foundUser.get().getName());
    }
}







