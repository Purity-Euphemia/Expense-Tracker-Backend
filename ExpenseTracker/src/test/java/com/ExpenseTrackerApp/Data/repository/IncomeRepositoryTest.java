package com.ExpenseTrackerApp.Data.repository;

import com.ExpenseTrackerApp.data.model.Income;
import com.ExpenseTrackerApp.data.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    void saveIncomeTest() {
        incomeRepository.deleteAll();
        assertEquals(0, incomeRepository.count());

        Income income = new Income();
        income.setUserId("user123");
        income.setAmount(1000.0);
        income.setSource("Job");
        income.setDate(LocalDate.of(2025, 10, 2));
        incomeRepository.save(income);
        assertEquals(1, incomeRepository.count());
    }

    @Test
    void findByUserIdTest() {
        incomeRepository.deleteAll();

        Income income1 = new Income();
        income1.setUserId("user123");
        income1.setAmount(500.0);
        income1.setSource("Freelance");
        income1.setDate(LocalDate.of(2025, 10, 1));

        Income income2 = new Income();
        income2.setUserId("user123");
        income2.setAmount(700.0);
        income2.setSource("Job");
        income2.setDate(LocalDate.of(2025, 10, 2));

        incomeRepository.save(income1);
        incomeRepository.save(income2);

        List<Income> incomes = incomeRepository.findByUserId("user123");
        assertEquals(2, incomes.size());
    }

}
