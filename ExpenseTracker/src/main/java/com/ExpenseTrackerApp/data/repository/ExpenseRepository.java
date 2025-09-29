package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseRepository {
    private final List<Expense> expenses = new ArrayList<>();

    public Expense save(Expense expense) {
        if (expense.getId() == 0) {
            expense.setId(expenses.size() + 1);
            expenses.add(expense);
        } else {
            deleteById(expense.getId());
            expenses.add(expense);
        }
        return expense;
    }

    public void deleteById(int id) {
        expenses.removeIf(expense -> expense.getId() == id);
    }

    public Expense findById(int id) {
        return expenses.stream().filter(expense -> expense.getId() == id).findFirst().orElse(null);
    }
    public List<Expense> findByUserId(int userId) {
        return expenses.stream().filter(expense -> expense.getUserId() == userId).collect(Collectors.toList());
    }
    public double getMonthlyTotal(int userId, int month, int year) {
        return expenses.stream().filter(expense -> expense.getUserId() == userId && expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year).mapToDouble(Expense::getAmount).sum();
    }
    public List<Expense> findByUserAndDateRange(int userId, LocalDate startDate, LocalDate endDate) {
        return expenses.stream().filter(expense -> expense.getUserId() == userId && (!expense.getDate().isBefore(startDate) && !expense.getDate().isAfter(endDate))).collect(Collectors.toList());
    }
}
