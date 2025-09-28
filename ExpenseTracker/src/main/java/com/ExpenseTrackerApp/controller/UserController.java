package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.exception.UserAlreadyExistsException;
import com.ExpenseTrackerApp.exception.UserNotFoundException;
import com.ExpenseTrackerApp.data.model.User;
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
        System.out.println("Registering user: " + user.getUsername());
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        User foundUser = userRepository.findById(user.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!foundUser.getPassword().equals(user.getPassword())){
           return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok("User logged in successfully");
    }



}
