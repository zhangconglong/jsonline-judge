package com.example.mq.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

/**
 * MQ消费者
 *
 * @author noodle
 */
@Service
public class MqConsumer {

    public static final String ISSUE_EXCHANGE = "issue.exchange";//交换机名
    public static final String ISSUE_QUEUE = "issue.queue";//消息队列名
    public static final String ROUTER_KEY = "issue";//消息队列名


    @Autowired
//    IssueESService issueESService;


    /**
     * 收到消息，插入ES
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = ISSUE_QUEUE),
            exchange = @Exchange(name = ISSUE_EXCHANGE, type = ExchangeTypes.DIRECT),
    key = {ROUTER_KEY}
    ))
    public void receiveIssue() {//MQMessage message

        Logger.info("RabbitMQ收到消息: ");
//        Logger.info("RabbitMQ收到消息: " + message);
//        Issue issue = message.getIssue();// 获取题目
//        issueESService.insertESIssue(issue);// 写入ES
    }
}
