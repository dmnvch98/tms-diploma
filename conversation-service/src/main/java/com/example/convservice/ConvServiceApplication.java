package com.example.convservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.convservice.client")
public class ConvServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvServiceApplication.class, args);
    }

}
