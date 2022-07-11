package com.awstest.DynamoDbSpringBootDemo.controller;

import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
import com.awstest.DynamoDbSpringBootDemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/makedeposit")
    public Transaction makeDeposit(string accountNumber, double amount){

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
