package com.example.common.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import java.io.Serializable;

/**
 * @version: v1.0
 * @description：消息实体体
 * @author: zhangconglong on 2021/11/21 21:38
 */
@Data
@TableName("message")
public class Message extends Model<Message> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 内容
      */
    private String content;

    /**
      * 评论人
      */
    private String username;

    /**
      * 评论时间
      */
    private String time;

    /**
      * 父ID
      */
    private Long parentId;

    @TableField(exist = false)
    private Message parentMessage;

    /**
     * 关联id
     */
    private Long foreignId;
    @TableField(exist = false)
    private String avatar;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}