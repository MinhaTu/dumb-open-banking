package com.awstest.DynamoDbSpringBootDemo.repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.awstest.DynamoDbSpringBootDemo.entity.Account;
import com.awstest.DynamoDbSpringBootDemo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Transaction save(Transaction transaction) {
        dynamoDBMapper.save(transaction);

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("src/main/resources/transaction " + transaction.getId()+".txt"));
            writer.write(transaction.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
        String bucketName = "minhatu-first-bucket";
        String keyName = "HELP.md";
        try {
            s3.putObject(bucketName, keyName, new File("src/main/resources/transaction " + transaction.getId()+".txt"));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }



        return transaction;
    }

    public Transaction getTransactionById(String id) {
        return dynamoDBMapper.load(Transaction.class, id);
    }



    public List<Transaction> getAccountTransactions(String id){

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":accN", new AttributeValue().withS(id));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("sourceAccountId = :accN").withExpressionAttributeValues(expressionAttributeValues);

        List<Transaction> accountTransactions = dynamoDBMapper.scan(Transaction.class, scanExpression);
        return  accountTransactions;
    }

    public List<Transaction> getAllTransactions() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<Transaction> transactions = dynamoDBMapper.scan(Transaction.class, scanExpression);
        return  transactions;
    }

}
