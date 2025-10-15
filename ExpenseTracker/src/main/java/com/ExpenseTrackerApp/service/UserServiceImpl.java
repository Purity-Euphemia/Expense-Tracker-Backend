package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.User;
import com.ExpenseTrackerApp.data.repository.UserRepository;
import com.ExpenseTrackerApp.dto.Request.RegisterUserRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateUserRequest;
import com.ExpenseTrackerApp.dto.Response.UserResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.exception.UserAlreadyExistsException;
import com.ExpenseTrackerApp.exception.UserNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        System.out.println("📧 Incoming email: " + registerUserRequest.getEmail());
        ValidationUtils.validateUser(registerUserRequest);

        if (userRepository.findByEmail(registerUserRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
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

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> resp = new ArrayList<>();
        for (User user : users) {
            resp.add(toResponse(user));
        }
        return resp;
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        return toResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UpdateUserRequest updateUserRequest) {
        User existing = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        if (updateUserRequest.getName() != null && !updateUserRequest.getName().isBlank()) {
            existing.setName(updateUserRequest.getName());
        }
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isBlank()) {
            existing.setEmail(updateUserRequest.getEmail());
        }

        User savedUser = userRepository.save(existing);
        return toResponse(savedUser);
    }

    @Override
    public String deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.deleteById(id);
        return "User deleted";
    }
}
