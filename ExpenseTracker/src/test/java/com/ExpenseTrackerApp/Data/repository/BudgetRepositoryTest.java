package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.Budget;
import com.ExpenseTrackerApp.data.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class BudgetRepositoryTest {

    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    void findAllBudgetsTest() {
        budgetRepository.deleteAll();
        assertEquals(0, budgetRepository.count());

        Budget budget = new Budget();
        budget.setUserId("user123");
        budget.setCategoryId("cat123");
        budget.setAmount(500.0);
        budget.setMonth(9);
        budget.setYear(2025);
        budgetRepository.save(budget);

        List<Budget> allBudgets = budgetRepository.findAll();

        assertEquals(1, allBudgets.size());
    }

}
