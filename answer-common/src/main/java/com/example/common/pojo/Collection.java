package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {

    @TableId(type = IdType.AUTO)
    private Integer collection_id;
    private Integer issue_id;
    private Integer user_id;

}