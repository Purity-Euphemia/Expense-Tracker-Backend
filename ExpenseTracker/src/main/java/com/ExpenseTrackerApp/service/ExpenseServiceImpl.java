package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.repository.ExpenseRepository;
import com.ExpenseTrackerApp.dto.Request.AddExpenseRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateExpenseRequest;
import com.ExpenseTrackerApp.dto.Response.AddExpenseResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
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

    @Override
    public List<AddExpenseResponse> getExpensesByUser(String userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        List<AddExpenseResponse> responses = new ArrayList<>();
        for (Expense expense : expenses) {
            responses.add(toResponse(expense));
        }
        return responses;
    }

    @Override
    public AddExpenseResponse updateExpense(String id, UpdateExpenseRequest updateExpenseRequest) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));

        if (updateExpenseRequest.getAmount() != null) existing.setAmount(updateExpenseRequest.getAmount());
        if (updateExpenseRequest.getCategory() != null) existing.setCategory(updateExpenseRequest.getCategory());
        if (updateExpenseRequest.getDescription() != null) existing.setDescription(updateExpenseRequest.getDescription());
        if (updateExpenseRequest.getDate() != null) existing.setDate(updateExpenseRequest.getDate());

        Expense savedExpense = expenseRepository.save(existing);
        return toResponse(savedExpense);
    }

    @Override
    public String deleteExpense(String id) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        expenseRepository.deleteById(id);
        return "Expense has been deleted";
    }

    @Override
    public double getMonthlyTotal(String userId, int month, int year) {
        List<Expense> list = expenseRepository.findByUserId(userId);
        double total = 0;
        for (Expense expense : list) {
            if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    @Override
    public List<AddExpenseResponse> getExpensesInRange(String userId, LocalDate startDate, LocalDate endDate) {
        List<Expense> filteredExpenses = expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        List<AddExpenseResponse> responses = new ArrayList<>();
        for (Expense expense : filteredExpenses) {
            responses.add(toResponse(expense));
        }
        return responses;
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
}
