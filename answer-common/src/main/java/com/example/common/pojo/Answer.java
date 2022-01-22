package com.example.common.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @TableId(type = IdType.AUTO)
    private Integer answerId;
    private Integer userId;
    private Integer issueId;
    private String answerContent;
    private Date answerTime;
    private Integer fans;
}