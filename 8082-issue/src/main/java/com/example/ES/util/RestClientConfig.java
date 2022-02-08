package com.example.ES.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * ES 客户端
 * author: luyi
 * time: 2022/1/22 18:44
 */
@Configuration
public class RestClientConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    /**
     * 返回客户端对象
     * @return
     */
    @Bean
    public RestHighLevelClient client() {
        System.out.println(host);
        System.out.println(port);
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(host, port,"http")
        ));
    }
}
