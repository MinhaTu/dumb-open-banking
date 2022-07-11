package com.awstest.DynamoDbSpringBootDemo.controller;

import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
import com.awstest.DynamoDbSpringBootDemo.repository.AccountRepository;
import com.awstest.DynamoDbSpringBootDemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;



    @PostMapping("/accounts")
    public Account postAccounts(@RequestBody Account account){
        return accountRepository.save(account);
    }
    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable("id") String accountId) {
        return accountRepository.getAccountById(accountId);
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") String employeeId) {
        return accountRepository.delete(employeeId);
    }

    @PutMapping("/accounts/{id}")
    public String updateAccount(@PathVariable("id") String accountId, @RequestBody Account account) {
        return accountRepository.update(accountId, account);
    }
    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable("id") String accountId) {
        return transactionRepository.getAccountTransactions(accountId);
    }

}
