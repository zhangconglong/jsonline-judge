package com.example.dao.vo;

import com.example.enums.CollectStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueCollectRecordVo {

    //题目的id
    private Integer issueId;
   // private Integer issueId;

   //点赞用户的id
    private Integer userId;

    //点赞的状态.默认未点赞
    private int status = CollectStatusEnum.UNLIKE.getCode();
}