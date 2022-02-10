package com.luyi.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将Bean注入到Spring工厂
 * author: luyi
 * time: 2022/2/7 15:42
 */
@Configuration
public class BeansConfig {

    /**
     * 管理RestTemplate
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
