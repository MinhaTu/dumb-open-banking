package com.awstest.DynamoDbSpringBootDemo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
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

    public Account getAccountById(String accountId) {
        return dynamoDBMapper.load(Account.class, accountId);
    }

    public String delete(String employeeId) {
        Transaction emp = dynamoDBMapper.load(Transaction.class, employeeId);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted";
    }

    public String update(String accountId, Account account) {
        dynamoDBMapper.save(account,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("employeeId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(accountId)
                                )));
        return accountId;
    }



}
