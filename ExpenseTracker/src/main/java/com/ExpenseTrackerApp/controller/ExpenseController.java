package com.ExpenseTrackerApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ExpenseResponse addExpense(@RequestBody AddExpenseRequest request) {
        return expenseController.addExpense(request);
    }

    @GetMapping
    public List<ExpenseResponse> getExpenses() {
        return expenseController.viewExpenses();
    }

    @PutMapping("/{expenseId}")
    public ExpenseResponse editExpense(@PathVariable String expenseId, @RequestBody AddExpenseRequest request) {
        return expenseController.editExpense(expenseId, request);
    }

    @DeleteMapping("/{expenseId}")
    public String deleteExpense(@PathVariable String expenseId) {
        return expenseController.deleteExpense(expenseId);
    }
}