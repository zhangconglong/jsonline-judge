package com.example.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.search.dao")
public class AnswerSearch8086Application {
    public static void main(String[] args) {
        SpringApplication.run(AnswerSearch8086Application.class, args);
    }
}
