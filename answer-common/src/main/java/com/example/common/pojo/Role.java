package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer roleId;
    private String roleName;
}