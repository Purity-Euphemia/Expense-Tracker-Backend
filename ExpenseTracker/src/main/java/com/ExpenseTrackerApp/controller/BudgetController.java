package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.AddBudgetRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateBudgetRequest;
import com.ExpenseTrackerApp.dto.Response.BudgetResponse;
import com.ExpenseTrackerApp.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService service = new BudgetService();

    @PostMapping
    public BudgetResponse setBudget(@RequestBody AddBudgetRequest addBudgetRequest) {
        return service.setBudget(addBudgetRequest);
    }
    @GetMapping("/user/{userId}")
    public List<BudgetResponse> getUserBudgets(@PathVariable("userId") int userId) {
        return service.getUserBudgets(userId);
    }
    @GetMapping("/user/{userId}/category/{categoryId}/month/{month}/year/{year}")
    public BudgetResponse getBudget(@PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId, @PathVariable("month") int month, @PathVariable("year") int year) {
        return service.getBudgetForUserCategoryMonth(userId, categoryId, month, year);
    }
    @PutMapping("/{id}")
    public BudgetResponse updateBudget(@PathVariable("id") int id, @RequestBody UpdateBudgetRequest updateBudgetRequest) {
        return service.updateBudget(id, updateBudgetRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteBudget(@PathVariable("id") int id) {
        return service.deleteBudget(id);
    }
}
