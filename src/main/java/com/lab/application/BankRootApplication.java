package com.lab.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BankRootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankRootApplication.class, args);
    }

}
