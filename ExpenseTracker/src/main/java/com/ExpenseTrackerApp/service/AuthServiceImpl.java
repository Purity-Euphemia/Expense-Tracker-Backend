package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.AuthToken;
import com.ExpenseTrackerApp.data.model.User;
import com.ExpenseTrackerApp.data.repository.AuthRepository;
import com.ExpenseTrackerApp.data.repository.UserRepository;
import com.ExpenseTrackerApp.dto.Request.LoginRequest;
import com.ExpenseTrackerApp.dto.Response.LoginResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        AuthToken token = new AuthToken();
        token.setUserEmail(user.getId());
        token.setToken(UUID.randomUUID().toString());
        authRepository.save(token);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserEmail(user.getId());
        loginResponse.setToken(token.getToken());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setMessage("Login successful");
        return loginResponse;
    }


    @Override
    public String logout(String tokenStr) {
        AuthToken token = authRepository.findByToken(tokenStr).orElseThrow(() -> new ResourceNotFoundException("Invalid token"));
        authRepository.delete(token);
        return "Logout successful";
    }
}
