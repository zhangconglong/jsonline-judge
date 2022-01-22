package com.example.common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户点赞表
 */
@TableName("collect")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueCollectRecord {

    //题目的id
    private Integer issueId;

   //点赞用户的id
    private Integer userId;
}