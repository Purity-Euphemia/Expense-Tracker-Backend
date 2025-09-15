package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.exception.UserAlreadyExistsException;
import com.ExpenseTrackerApp.model.User;
import com.ExpenseTrackerApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }



}
