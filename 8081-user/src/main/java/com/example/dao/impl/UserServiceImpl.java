package com.example.dao.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.config.Result;
import com.example.dao.mapper.UserMapper;
import com.example.common.pojo.User;
import com.example.dao.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * token的合法性校验
     * @return
     */
    private Result<?> findUserByToken(){
        return Result.success();
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    /**
     * 根据token解析出用户信息
     * @param token
     * @return
     */
    @Override
    public User findUserByToken(String token) {

        if (StringUtils.isBlank(token)){
            return null;
        }
//        Map<String, Object> map = JwtUtils.checkToken(token);

        //从redis中取出token
        String userJson = redisTemplate.opsForValue().get(token);

        System.out.println("userJson = " + userJson);

        if (StringUtils.isBlank(userJson)){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //将JSON字符串转化成java对象
            return objectMapper.readValue(userJson, new TypeReference<User>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据邮箱
     * @param email
     * @param password
     * @return
     */
    @Override
    public User findUserByEmail(String email, String password) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .eq(User::getPassword, password));

        return res;
    }
}
