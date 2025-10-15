package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.Expense;
import com.ExpenseTrackerApp.data.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void saveAndFindByUserIdTest() {
        expenseRepository.deleteAll();

        Expense expense = new Expense();
        expense.setUserId("user123");
        expense.setAmount(250.0);
        expense.setCategory("Food");
        expense.setDate(LocalDate.of(2025, 10, 2));
        expense.setDescription("Lunch");

        expenseRepository.save(expense);

        List<Expense> expenses = expenseRepository.findByUserId("user123");

        assertEquals(1, expenses.size());
        assertEquals("Food", expenses.get(0).getCategory());
    }
}

