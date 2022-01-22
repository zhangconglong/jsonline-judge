package com.example.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String username;
    private String password;
    private String email;
    // 用于临时存放验证码
    private String code;

    public UserVo(String email, String password) {
        this.email = email;
        this.password = password;
    }
}