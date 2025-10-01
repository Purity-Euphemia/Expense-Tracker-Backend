package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateUserRequest;
import com.ExpenseTrackerApp.dto.Response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(RegisterUserRequest registerUserRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(String id);
    UserResponse updateUser(String id, UpdateUserRequest updateUserRequest);
    String deleteUser(String id);
}
