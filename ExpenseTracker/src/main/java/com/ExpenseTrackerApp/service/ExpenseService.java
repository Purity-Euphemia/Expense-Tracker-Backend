package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.repository.ExpenseRepository;
import com.ExpenseTrackerApp.dto.Request.AddExpenseRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateExpenseRequest;
import com.ExpenseTrackerApp.dto.Response.AddExpenseResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    public AddExpenseResponse addExpense(AddExpenseRequest addExpenseRequest) {
        ValidationUtils.validateExpense(addExpenseRequest);

        Expense expense = new Expense();
        expense.setUserId(addExpenseRequest.getUserId());
        expense.setAmount(addExpenseRequest.getAmount());
        expense.setCategory(addExpenseRequest.getCategory());
        expense.setDescription(addExpenseRequest.getDescription());
        expense.setDate(addExpenseRequest.getDate());

        Expense savedExpense = expenseRepository.save(expense);
        return toResponse(savedExpense);
    }
    private AddExpenseResponse toResponse(Expense expense) {
        AddExpenseResponse addExpenseResponse = new AddExpenseResponse();
        addExpenseResponse.setId(expense.getId());
        addExpenseResponse.setUserId(expense.getUserId());
        addExpenseResponse.setAmount(expense.getAmount());
        addExpenseResponse.setCategory(expense.getCategory());
        addExpenseResponse.setDescription(expense.getDescription());
        addExpenseResponse.setDate(expense.getDate());
        return addExpenseResponse;
    }
    public List<AddExpenseResponse> getExpensesByUser(int userId) {
        List<Expense> list = expenseRepository.findByUserId(userId);
        return list.stream().map(this::toResponse).collect(Collectors.toList());
    }
    public AddExpenseResponse updateExpense(int id, UpdateExpenseRequest updateExpenseRequest) {
        Expense existing = expenseRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Expense not found" + id);
        }
        if (updateExpenseRequest.getAmount() != null) existing.setAmount(updateExpenseRequest.getAmount());
        if (updateExpenseRequest.getCategory() != null) existing.setCategory(updateExpenseRequest.getCategory());
        if (updateExpenseRequest.getDescription() != null) existing.setDescription(updateExpenseRequest.getDescription());
        if (updateExpenseRequest.getDate() != null) existing.setDate(updateExpenseRequest.getDate());

        Expense savedExpense = expenseRepository.save(existing);
        return toResponse(savedExpense);
    }
    public String deleteExpense(int id) {
        Expense existing = expenseRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Expense not found with id " + id);
        }
        expenseRepository.deleteById(id);
        return "Expense has been deleted";
    }
    public double getMonthlyTotal(int userId, int month, int year) {
        return expenseRepository.getMonthlyTotal(userId, month, year);
    }
    public List<AddExpenseResponse> getExpensesInRange(int userId, LocalDate startDate, LocalDate endDate) {
        List<Expense> filteredExpenses = expenseRepository.findByUserAndDateRange(userId, startDate, endDate);
        return filteredExpenses.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
