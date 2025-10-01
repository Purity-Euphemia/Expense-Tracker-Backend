package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.LoginRequest;
import com.ExpenseTrackerApp.dto.Response.LoginResponse;
import com.ExpenseTrackerApp.service.AuthServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl service = new AuthServiceImpl();

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String token) {
        service.logout(token);
        return "Logged out";
    }
}

