package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addTransaction(Expense transaction) {
        transactionRepository.save(transaction);
    }

    public List<Expense> getTransactionByUsername(String username){
        return transactionRepository.findByUsername(username);
    }
    public void deleteTransaction(String username, String id){
        Expense transaction = transactionRepository.findById(id).filter(t -> t.getUsername().equals(username)).orElse(null);

        if(transaction != null){
            transactionRepository.deleteById(id);
        }
    }
}
