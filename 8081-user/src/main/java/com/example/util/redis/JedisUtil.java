//package com.example.util.redis;
//
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.ResourceBundle;
//
///**
// * 从jedis连接池中取出jedis使用
// */
//public class JedisUtil {
//
//    private static JedisPool jp = null;
//
//    static {
//        ResourceBundle rb = ResourceBundle.getBundle("application");//获取redis.properties
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(Integer.parseInt(rb.getString("spring.redis.maxTotal")));
//        jedisPoolConfig.setMaxIdle(Integer.parseInt(rb.getString("spring.redis.maxTdle")));
//
//        JedisPool jp = new JedisPool(jedisPoolConfig, rb.getString("spring.redis.host"), Integer.parseInt(rb.getString("spring.redis.port")));
//    }
//
//    public static Jedis getJedis(){
//        return jp.getResource();
//    }
//}
