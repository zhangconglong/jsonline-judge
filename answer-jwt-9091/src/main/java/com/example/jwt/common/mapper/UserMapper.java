package com.example.jwt.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}