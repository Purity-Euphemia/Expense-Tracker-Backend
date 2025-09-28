package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(users.size() + 1);
            users.add(user);
        } else {
            deleteById(user.getId());
            users.add(user);
        }
        return user;
    }

    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public User findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public List<User> findAll() {
        return users;
    }
}
