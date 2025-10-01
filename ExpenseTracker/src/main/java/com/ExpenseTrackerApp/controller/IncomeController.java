package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.dto.Request.AddIncomeRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateIncomeRequest;
import com.ExpenseTrackerApp.dto.Response.IncomeResponse;
import com.ExpenseTrackerApp.service.IncomeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    private final IncomeServiceImpl service = new IncomeServiceImpl();

    @PostMapping
    public IncomeResponse addIncome(@RequestBody AddIncomeRequest addIncomeRequest) {
        return service.addIncome(addIncomeRequest);
    }

    @GetMapping("/user/{userId}")
    public List<IncomeResponse> getByUser(@PathVariable("userId") int userId) {
        return service.getIncomeByUser(userId);
    }
    @PutMapping("/{id}")
    public IncomeResponse updateIncome(@PathVariable("id") int id, @RequestBody UpdateIncomeRequest updateIncomeRequest) {
        return service.updateIncome(id, updateIncomeRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteIncome(@PathVariable("id") int id) {
        return service.deleteIncome(id);
    }
    @GetMapping("/user/{userId}/monthly")
    public double monthly(@PathVariable("userId") int userId, @RequestParam int month, @RequestParam int year) {
        return service.getMonthlyIncome(userId, month, year);
    }
    @GetMapping("/user/{userId}/range")
    public List<IncomeResponse> range(@PathVariable("userId") int userId, @RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return service.getIncomeInRange(userId, startDate, endDate);
    }
}
