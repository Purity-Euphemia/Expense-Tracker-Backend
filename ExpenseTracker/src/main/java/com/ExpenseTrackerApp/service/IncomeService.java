package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.dto.Request.AddIncomeRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateIncomeRequest;
import com.ExpenseTrackerApp.dto.Response.IncomeResponse;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    IncomeResponse addIncome(AddIncomeRequest request);
    List<IncomeResponse> getIncomeByUser(String userEmail);
    List<IncomeResponse> getAllIncome();
    IncomeResponse updateIncome(String id, UpdateIncomeRequest updateIncomeRequest);
    String deleteIncome(String id);
    double getMonthlyIncome(String userEmail, int month, int year);
    List<IncomeResponse> getIncomeInRange(String userEmail, LocalDate start, LocalDate end);
}
