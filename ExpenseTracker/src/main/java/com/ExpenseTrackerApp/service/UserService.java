package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;
import com.ExpenseTrackerApp.dto.Response.UserResponse;

public interface UserService {
    UserResponse register(RegisterUserRequest request);
    UserResponse findUserByEmail(String email);
    String deleteUser(String id);
}
