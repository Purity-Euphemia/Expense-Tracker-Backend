package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Income;
import com.ExpenseTrackerApp.data.repository.IncomeRepository;
import com.ExpenseTrackerApp.dto.Request.AddIncomeRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateIncomeRequest;
import com.ExpenseTrackerApp.dto.Response.IncomeResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeService {
    private final IncomeRepository incomeRepository = new IncomeRepository();

    public IncomeResponse addIncome(AddIncomeRequest addIncomeRequest) {
        ValidationUtils.validateIncome(addIncomeRequest);

        Income income = new Income();
        income.setAmount(addIncomeRequest.getAmount());
        income.setSource(addIncomeRequest.getSource());
        income.setDate(addIncomeRequest.getDate());

        Income saved = incomeRepository.save(income);
        return toResponse(saved);
    }
    public List<IncomeResponse> getIncomeByUser(int userId) {
        List<Income> list = incomeRepository.findByUserId(userId);
        List<IncomeResponse> responses = new ArrayList<>();
        for (Income inc : list) {
            responses.add(toResponse(inc));
        }
        return responses;
    }

    public List<IncomeResponse> getAllIncome() {
        List<Income> incomes = incomeRepository.findAll();
        List<IncomeResponse> responses = new ArrayList<>();
        for (Income inc : incomes) {
            responses.add(toResponse(inc));
        }
        return responses;
    }

    public IncomeResponse updateIncome(int id, UpdateIncomeRequest updateIncomeRequest) {
        Income existing = incomeRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Income not found with id " + id);
        }
        if (updateIncomeRequest.getAmount() != null) {
            existing.setAmount(updateIncomeRequest.getAmount());
        }
        if (updateIncomeRequest.getSource() != null && !updateIncomeRequest.getSource().isBlank()) {
            existing.setSource(updateIncomeRequest.getSource());
        }
        if (updateIncomeRequest.getDate() != null) {
            existing.setDate(updateIncomeRequest.getDate());
        }
        Income saved = incomeRepository.save(existing);
        return toResponse(saved);
    }

    public String deleteIncome(int id) {
        Income existing = incomeRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Income not found with id " + id);
        }
        incomeRepository.deleteById(id);
        return "Deleted income";
    }
    public double getMonthlyIncome(int userId, int month, int year) {
        return incomeRepository.getMonthlyTotal(userId, month, year);
    }

    public List<IncomeResponse> getIncomeInRange(int userId, LocalDate start, LocalDate end) {
        List<Income> list = incomeRepository.findByUserAndDateRange(userId, start, end);
        List<IncomeResponse> responses = new ArrayList<>();
        for (Income income : list) {
            responses.add(toResponse(income));
        }
        return responses;
    }
    private IncomeResponse toResponse(Income income) {
        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setId(income.getId());
        incomeResponse.setAmount(income.getAmount());
        incomeResponse.setSource(income.getSource());
        incomeResponse.setDate(income.getDate());
        return incomeResponse;
    }
}
