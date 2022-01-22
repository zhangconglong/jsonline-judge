//package com.example.common.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    //自定义redisTemplate
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
//
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//
//        //配置具体的序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        template.setKeySerializer(jackson2JsonRedisSerializer);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//        //key：String
//        template.setKeySerializer(stringRedisSerializer);
//        //HashKey：String
//        template.setHashKeySerializer(stringRedisSerializer);
//        //value的序列化也采用Jackson
//        template.setValueSerializer(stringRedisSerializer);
//        template.setHashValueSerializer(stringRedisSerializer);
//
//        return template;
//    }
//}
