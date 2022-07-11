package com.awstest.DynamoDbSpringBootDemo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Transaction save(Transaction transaction) {
        dynamoDBMapper.save(transaction);
        return transaction;
    }

    public Transaction getTransactionById(String transactionId) {
        return dynamoDBMapper.load(Transaction.class, transactionId);
    }
    /*
    public List<Transaction> getAccountTransactions(String accountNumber){

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withN(accountNumber));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("accountNumber = :val1").withExpressionAttributeValues(eav);

        List<Book> scanResult = mapper.scan(Book.class, scanExpression);


    }

   */
}
