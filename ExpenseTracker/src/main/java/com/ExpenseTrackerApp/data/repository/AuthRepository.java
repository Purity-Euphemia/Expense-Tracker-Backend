package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.AuthToken;

import java.util.ArrayList;
import java.util.List;

public class AuthRepository {
    private final List<AuthToken> tokens = new ArrayList<>();

    public AuthToken save(AuthToken token) {
        tokens.add(token);
        return token;
    }
    public AuthToken findByToken(String token) {
        return tokens.stream().filter(authToken -> authToken.getToken().equals(token)).findFirst().orElse(null);
    }
    public void deleteByToken(String token) {
        tokens.removeIf(authToken -> authToken.getToken().equals(token));
    }
}
