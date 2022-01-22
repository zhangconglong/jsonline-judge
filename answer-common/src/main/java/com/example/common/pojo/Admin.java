package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @TableId(type = IdType.AUTO)
    private Integer rootId;
    private String adminName;
    private String adminEmail;
    private String password;
    private String perms;
}
