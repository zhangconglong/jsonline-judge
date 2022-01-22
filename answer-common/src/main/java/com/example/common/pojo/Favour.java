package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("favour")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Favour{

    // @TableId(type = IdType.AUTO)
    private Integer issueId;
    private Integer favour_count;

}