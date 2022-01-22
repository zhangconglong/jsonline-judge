package com.example.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @version: v1.0
 * @description：布隆过滤器？
 * @author: zhangconglong on 2021/11/27 20:33
 */
@ConfigurationProperties("bloom.filter")
@Component
public class RedisBloomFilter {
    private long MAX_INSERT;//预计的最大插入量

    private double fpp;//可接受的容错率

    private long numBits;//bit数组长度

    private RedisTemplate redisTemplate;


    public RedisBloomFilter() {
    }
}