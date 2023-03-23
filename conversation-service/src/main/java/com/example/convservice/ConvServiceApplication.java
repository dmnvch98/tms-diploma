package com.example.convservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "com.example.convservice.client")
public class ConvServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConvServiceApplication.class, args);
    }

}
