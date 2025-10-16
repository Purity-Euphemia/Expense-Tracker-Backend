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
        System.out.println(">> AddExpenseRequest received: " + addExpenseRequest);
        System.out.println(">> userId: " + addExpenseRequest.getUserEmail());


        ValidationUtils.validateExpense(addExpenseRequest);


        if (!addExpenseRequest.getUserEmail().contains("@")) {
            System.out.println("⚠️ WARNING: userId does not appear to be an email address!");
        }


        Expense expense = new Expense();
        expense.setUserEmail(addExpenseRequest.getUserEmail());
        expense.setAmount(addExpenseRequest.getAmount());
        expense.setCategory(addExpenseRequest.getCategory());
        expense.setDescription(addExpenseRequest.getDescription());
        expense.setDate(addExpenseRequest.getDate());

        System.out.println(">> Expense to save: " + expense);

        Expense savedExpense = expenseRepository.save(expense);

        System.out.println(">> Saved expense: " + savedExpense);

        return toResponse(savedExpense);
    }

    @Override
    public List<AddExpenseResponse> getExpensesByUser(String userEmail) {
        System.out.println("Fetching expenses for userId: " + userEmail);

        List<Expense> expenses = expenseRepository.findByUserId(userEmail);
        System.out.println("Found " + expenses.size() + " expenses for user: " + userEmail);

        List<AddExpenseResponse> responses = new ArrayList<>();
        for (Expense expense : expenses) {
            System.out.println("Expense: " + expense);
            responses.add(toResponse(expense));
        }
        return responses;
    }

    @Override
    public AddExpenseResponse updateExpense(String id, UpdateExpenseRequest updateExpenseRequest) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));

        if (updateExpenseRequest.getAmount() != null)
            existing.setAmount(updateExpenseRequest.getAmount());
        if (updateExpenseRequest.getCategory() != null)
            existing.setCategory(updateExpenseRequest.getCategory());
        if (updateExpenseRequest.getDescription() != null)
            existing.setDescription(updateExpenseRequest.getDescription());
        if (updateExpenseRequest.getDate() != null)
            existing.setDate(updateExpenseRequest.getDate());

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
    public double getMonthlyTotal(String userEmail, int month, int year) {
        List<Expense> list = expenseRepository.findByUserId(userEmail);
        double total = 0;
        for (Expense expense : list) {
            if (expense.getDate().getMonthValue() == month &&
                    expense.getDate().getYear() == year) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    @Override
    public List<AddExpenseResponse> getExpensesInRange(String userEmail, LocalDate startDate, LocalDate endDate) {
        List<Expense> filteredExpenses = expenseRepository.findByUserIdAndDateBetween(userEmail, startDate, endDate);
        List<AddExpenseResponse> responses = new ArrayList<>();
        for (Expense expense : filteredExpenses) {
            responses.add(toResponse(expense));
        }
        return responses;
    }

    private AddExpenseResponse toResponse(Expense expense) {
        AddExpenseResponse addExpenseResponse = new AddExpenseResponse();
        addExpenseResponse.setId(expense.getId());
        addExpenseResponse.setAmount(expense.getAmount());
        addExpenseResponse.setCategory(expense.getCategory());
        addExpenseResponse.setDescription(expense.getDescription());
        addExpenseResponse.setDate(expense.getDate());
        addExpenseResponse.setMessage("Expense added successfully");
        return addExpenseResponse;
    }
}
