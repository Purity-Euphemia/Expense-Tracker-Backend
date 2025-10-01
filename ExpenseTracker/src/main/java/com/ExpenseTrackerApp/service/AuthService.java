package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.AuthToken;
import com.ExpenseTrackerApp.data.model.User;
import com.ExpenseTrackerApp.data.repository.AuthRepository;
import com.ExpenseTrackerApp.data.repository.UserRepository;
import com.ExpenseTrackerApp.dto.Request.LoginRequest;
import com.ExpenseTrackerApp.dto.Response.LoginResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;

import java.util.UUID;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();
    private final AuthRepository authRepository = new AuthRepository();

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        AuthToken token = new AuthToken();
        token.setUserId(user.getId());
        token.setToken(UUID.randomUUID().toString());
        authRepository.save(token);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setToken(token.getToken());
        return loginResponse;
    }
    public String logout(String tokenStr) {
        AuthToken token = authRepository.findByToken(tokenStr);
        if (token == null) {
            throw new ResourceNotFoundException("Invalid token");
        }
       return "Logout successful";
    }
}
