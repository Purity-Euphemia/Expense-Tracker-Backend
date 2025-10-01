package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateUserRequest;
import com.ExpenseTrackerApp.dto.Response.UserResponse;
import com.ExpenseTrackerApp.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService = new UserServiceImpl();

    @PostMapping
    public UserResponse register(@RequestBody RegisterUserRequest registerUserRequest) {
        return userService.registerUser(registerUserRequest);
    }
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "User has been deleted";
    }

}
