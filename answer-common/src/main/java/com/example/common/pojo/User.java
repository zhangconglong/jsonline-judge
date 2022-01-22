package com.example.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 */
@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long userId; //使用雪花算法生成
    private String userName;
    private String email;
    private String password;
    private Integer comment_count;
    private String avatar; //用户的头像
    private String perms;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
