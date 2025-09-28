package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User save(User user){
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public User findByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> findAll() {
        return users;
    }
}
