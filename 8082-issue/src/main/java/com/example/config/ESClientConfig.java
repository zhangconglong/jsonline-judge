package com.example.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearchClientConfig配置服务器
 * author: luyi
 * time: 2022/1/22 18:44
 */
@Configuration
public class ESClientConfig {

    /**
     * 返回客户端对象
     * @return
     */
    @Bean
    public RestHighLevelClient getClient(){
        return new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")));
    }
}
