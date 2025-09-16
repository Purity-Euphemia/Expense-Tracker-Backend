package com.ExpenseTrackerApp.controller;

import com.ExpenseTrackerApp.model.Transaction;
import com.ExpenseTrackerApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok("Transaction added successfully");
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable String username){
        return ResponseEntity.ok(transactionService.getTransactionByUser(username))
    }
    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String username, @PathVariable String id){
        transactionService.deleteTransaction(username, id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
