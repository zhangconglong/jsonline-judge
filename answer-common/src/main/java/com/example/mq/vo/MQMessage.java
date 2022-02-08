package com.example.mq.vo;

import com.example.common.pojo.Issue;

import java.io.Serializable;

/**
 * 在MQ中传递的【秒杀】信息
 *
 * @author noodle
 */
public class MQMessage implements Serializable{

    private Issue issue;

//    private long goodsId;


    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

//    public long getGoodsId() {
//        return goodsId;
//    }
//
//    public void setGoodsId(long goodsId) {
//        this.goodsId = goodsId;
//    }
    
}
