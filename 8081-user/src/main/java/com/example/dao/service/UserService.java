package com.example.dao.service;

import com.example.common.pojo.User;

public interface UserService {
    //根据id查询
    User findUserById(int id);
    //根据token查询
    User findUserByToken(String token);
    //根据邮件和密码
    User findUserByEmail(String email, String password);
    //
}