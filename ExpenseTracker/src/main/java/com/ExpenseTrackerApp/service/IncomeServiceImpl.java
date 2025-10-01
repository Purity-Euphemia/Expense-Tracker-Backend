package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.data.model.Income;
import com.ExpenseTrackerApp.data.repository.IncomeRepository;
import com.ExpenseTrackerApp.dto.Request.AddIncomeRequest;
import com.ExpenseTrackerApp.dto.Request.UpdateIncomeRequest;
import com.ExpenseTrackerApp.dto.Response.IncomeResponse;
import com.ExpenseTrackerApp.exception.ResourceNotFoundException;
import com.ExpenseTrackerApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public IncomeResponse addIncome(AddIncomeRequest addIncomeRequest) {
        ValidationUtils.validateIncome(addIncomeRequest);

        Income income = new Income();
        income.setAmount(addIncomeRequest.getAmount());
        income.setSource(addIncomeRequest.getSource());
        income.setDate(addIncomeRequest.getDate());

        Income saved = incomeRepository.save(income);
        return toResponse(saved);
    }
    @Override
    public List<IncomeResponse> getIncomeByUser(String userId) {
        List<Income> incomes = incomeRepository.findByUserId(userId);
        List<IncomeResponse> responses = new ArrayList<>();
        for (Income income : incomes) {
            responses.add(toResponse(income));
        }
        return responses;
    }
    @Override
    public List<IncomeResponse> getAllIncome() {
        List<Income> incomes = incomeRepository.findAll();
        List<IncomeResponse> responses = new ArrayList<>();
        for (Income inc : incomes) {
            responses.add(toResponse(inc));
        }
        return responses;
    }
    @Override
    public IncomeResponse updateIncome(String id, UpdateIncomeRequest updateIncomeRequest) {
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
    @Override
    public String deleteIncome(String id) {
        Income existing = incomeRepository.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Income not found with id " + id);
        }
        incomeRepository.deleteById(id);
        return "Deleted income";
    }
    @Override
    public double getMonthlyIncome(String userId, int month, int year) {
        List<Income> incomes = incomeRepository.findByUserId(userId);
        double total = 0;
        for (Income income : incomes) {
            if (income.getDate().getMonthValue() == month && income.getDate().getYear() == year) {
                total += income.getAmount();
            }
        }
        return total;
    }

    @Override
    public List<IncomeResponse> getIncomeInRange(String userId, LocalDate start, LocalDate end) {
        List<Income> list = incomeRepository.findByUserIdAndDateBetween(userId, start, end);
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
