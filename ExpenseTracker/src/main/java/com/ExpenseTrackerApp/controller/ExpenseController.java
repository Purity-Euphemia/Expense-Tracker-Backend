package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.AddExpenseRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateExpenseRequest;
import com.ExpenseTrackerApp.dto.Response.AddExpenseResponse;
import com.ExpenseTrackerApp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public AddExpenseResponse addExpense(@RequestBody AddExpenseRequest addExpenseRequest) {
        return expenseService.addExpense(addExpenseRequest);
    }

    @GetMapping("/user/{userId}")
    public List<AddExpenseResponse> getByUser(@PathVariable("userId") String userId) {
        return expenseService.getExpensesByUser(userId);
    }

    @PutMapping("/{id}")
    public AddExpenseResponse updateExpense(@PathVariable("id") String id, @RequestBody UpdateExpenseRequest updateExpenseRequest) {
        return expenseService.updateExpense(id, updateExpenseRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable("id") String id) {
        return expenseService.deleteExpense(id);
    }

    @GetMapping("/user/{userId}/monthly")
    public double getMonthlyTotal(@PathVariable("userId") String userId, @RequestParam("month") int month, @RequestParam("year") int year) {
        return expenseService.getMonthlyTotal(userId, month, year);
    }

    @GetMapping("/user/{userId}/range")
    public List<AddExpenseResponse> getInRange(@PathVariable("userId") String userId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        LocalDate startDateParsed = LocalDate.parse(startDate);
        LocalDate endDateParsed = LocalDate.parse(endDate);
        return expenseService.getExpensesInRange(userId, startDateParsed, endDateParsed);
    }
}