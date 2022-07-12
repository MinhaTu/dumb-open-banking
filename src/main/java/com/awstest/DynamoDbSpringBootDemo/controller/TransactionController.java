package com.awstest.DynamoDbSpringBootDemo.controller;

import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
import com.awstest.DynamoDbSpringBootDemo.repository.AccountRepository;
import com.awstest.DynamoDbSpringBootDemo.repository.TransactionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.io.FileWriter;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;



    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }


    @PostMapping("transactions/transfer")
    public Transaction transfer(@RequestBody Transaction transaction){

        String sourceAccountId = transaction.getSourceAccountId();
        String destinationAccountId = transaction.getDestinationAccountId();
        Double amount = transaction.getTransactionAmount();

        Account sAccount = accountRepository.getAccountById(sourceAccountId);
        Account dAccount = accountRepository.getAccountById(destinationAccountId);
        if(sAccount.getCurrentBalance()>=amount){
            sAccount.setCurrentBalance(sAccount.getCurrentBalance()-amount);
            dAccount.setCurrentBalance(dAccount.getCurrentBalance()+amount);
            accountRepository.update(sourceAccountId,sAccount);
            accountRepository.update(destinationAccountId,dAccount);

            transaction.setWasSuccessful(Boolean.TRUE);
            transaction.setDate(new Date());
            return  transactionRepository.save(transaction);
        }
        transaction.setDate(new Date());
        transaction.setWasSuccessful(Boolean.FALSE);



        return  transactionRepository.save(transaction);
    }



    /*
     [HttpPost]
        [Route("make_deposit")]
        public IActionResult MakeDeposit(string AccountNumber, decimal Amount, string TransactionPin)
        {
            return Ok(_transactionService.MakeDeposit(AccountNumber, Amount, TransactionPin));
        }
        [HttpPost]
        [Route("make_funds_transfer")]
        public IActionResult MakeFundsTransfer(string FromAccount, string ToAccount, decimal Amount, string TransactionPin)
        {
            if (FromAccount.Equals(ToAccount)) return BadRequest("You cannot transfer money to yourself");

            return Ok(_transactionService.MakeFundsTransfer(FromAccount, ToAccount, Amount, TransactionPin));
        }
     */

}
