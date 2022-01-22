package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("perm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perm {
    private Integer permId;
    private String permName;
}