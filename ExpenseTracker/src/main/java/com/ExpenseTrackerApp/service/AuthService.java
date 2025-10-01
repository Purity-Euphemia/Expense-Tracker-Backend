package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.LoginRequest;
import com.ExpenseTrackerApp.dto.Response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    String logout(String token);
}
