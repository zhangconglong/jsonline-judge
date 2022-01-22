package com.example;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.example.dao.mapper")
public class SpringCloudIssueServer8082{
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudIssueServer8082.class, args);
    }
}
