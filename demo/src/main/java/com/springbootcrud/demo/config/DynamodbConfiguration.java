package com.springbootcrud.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamodbConfiguration {

    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                      new AwsClientBuilder.EndpointConfiguration(
                              "dynamodb.eu-north-1.amazonaws.com",
                              "eu-north-1"
                      )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "AKIAW3MECNJJ6JSNJGOE",
                                        "RzT9Fb7xSqg06IOzIFFIE3PbEaP90QmSE/WHBXIh"
                                )
                        )
                )
                .build();

    }
}
