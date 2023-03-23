package com.example.convservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSQSConfig {
    @Value("${aws.region}")
    private String AWS_REGION;
    @Value("${services.queue.url}")
    private String QUEUE_ENDPOINT;
    @Value("${aws.access_key}")
    private String login;
    @Value("${aws.secret_key}")
    private String password;

    @Bean
    public AmazonSQS amazonSqs() {
        final AmazonSQSClientBuilder amazonSqsClientBuilder = AmazonSQSClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(login, password)));
        amazonSqsClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(QUEUE_ENDPOINT, AWS_REGION));

        return amazonSqsClientBuilder.build();
    }

}
