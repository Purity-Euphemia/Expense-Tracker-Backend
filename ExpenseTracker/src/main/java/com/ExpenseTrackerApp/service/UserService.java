package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.User;
import com.ExpenseTrackerApp.data.repository.UserRepository;
import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateUserRequest;
import com.ExpenseTrackerApp.dto.Response.UserResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;


import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        ValidationUtils.validateUser(registerUserRequest);

        if (userRepository.findByEmail(registerUserRequest.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setName(registerUserRequest.getName());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());

        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }
    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        return toResponse(user);
    }

    public UserResponse updateUser(int id, UpdateUserRequest updateUserRequest) {
        User existing = userRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        if (updateUserRequest.getName() != null && !updateUserRequest.getName().isBlank()) {
            existing.setName(updateUserRequest.getName());
        }
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isBlank()) {
            existing.setEmail(updateUserRequest.getEmail());
        }
        User savedUser = userRepository.save(existing);
        return toResponse(savedUser);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

}
