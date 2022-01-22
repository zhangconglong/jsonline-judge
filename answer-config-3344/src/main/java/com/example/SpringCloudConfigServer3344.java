package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer //开启
public class SpringCloudConfigServer3344 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServer3344.class, args);
    }
}