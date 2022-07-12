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

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
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
    public String updateAccount(@PathVariable("id") String id, @RequestBody Account account) {
        return accountRepository.update(id, account);
    }

    @PutMapping("/accounts/{id}/deposit/{amount}")
    public Account accountDeposit(@PathVariable("id") String id,@PathVariable("amount") Double amount) {
        return accountRepository.deposit(id, amount);
    }
    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable("id") String id) {
        return transactionRepository.getAccountTransactions(id);
    }

}
