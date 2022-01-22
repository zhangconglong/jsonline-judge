package com.example.dao.vo;

import com.example.common.pojo.User;

public class UserVoToUser {
    /**
     * 剔除表单中的code
     */
    public static User toUser(UserVo userVo) {

        //创建一个数据库中存储的对象
        User user = new User();

        //传值
        user.setUserName(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());

        // 返回包装后的对象
        return user;
    }
}