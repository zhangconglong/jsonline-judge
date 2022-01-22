package com.example.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version: v1.0
 * @description：沙箱支付模块
 * @author: zhangconglong on 2021/11/24 16:24
 */
@SpringBootApplication
@MapperScan("com.example.pay.mapper")
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
