package com.luyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 消息队列服务
 *
 * @author noodle
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class Config7001 {
    public static void main(String[] args) {
        SpringApplication.run(Config7001.class, args);
    }
}
