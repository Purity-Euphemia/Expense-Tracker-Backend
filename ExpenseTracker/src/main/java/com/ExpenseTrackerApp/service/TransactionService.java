package com.ExpenseTrackerApp.service;

import com.ExpenseTrackerApp.model.Transaction;
import com.ExpenseTrackerApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionByUsername(String username){
        return transactionRepository.findByUsername(username);
    }
    public void deleteTransaction(String username, String id){
        Transaction transaction = transactionRepository.findById(id).filter(t -> t.getUsername().equals(username)).orElse(null);

        if(transaction != null){
            transactionRepository.deleteById(id);
        }
    }
}
