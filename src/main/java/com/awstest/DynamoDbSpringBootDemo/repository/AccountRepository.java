package com.awstest.DynamoDbSpringBootDemo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Account save(Account account) {
        dynamoDBMapper.save(account);
        return account;
    }

    public Account getAccountById(String id) {
        return dynamoDBMapper.load(Account.class, id);
    }

    public String delete(String id) {
        Account acc = dynamoDBMapper.load(Account.class, id);
        dynamoDBMapper.delete(acc);
        return "Employee Deleted";
    }

    public Account deposit(String id,Double amount){
        Account acc = getAccountById(id);
        acc.setCurrentBalance(acc.getCurrentBalance()+amount);
        return acc;
    }

    public String update(String id, Account account) {
        dynamoDBMapper.save(account,
                new DynamoDBSaveExpression()
           .withExpectedEntry("id",
                  new ExpectedAttributeValue(
                        new AttributeValue().withS(id)
                                )));
        return id;
    }


    public List<Account> getAllAccounts() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<Account> accounts = dynamoDBMapper.scan(Account.class, scanExpression);
        return  accounts;
    }
}
