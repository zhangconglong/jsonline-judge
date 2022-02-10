package com.example.mq;

import com.example.mq.vo.MQMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * MqProducorService
 * author: luyi
 * time: 2022/2/10 23:28
 */
@Service
public class MqProducerService {
    @Value("${issue.exchange}")//交换机名
    public String ISSUE_EXCHANGE ;

    @Value("${issue.queue}")//消息队列名
    public String ISSUE_QUEUE ;

    @Value("${queue.key}")//消息队列名
    public String ROUTER_KEY;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MQMessage message){

        /*创建消息，使用链式调用*/
        Message message2 = MessageBuilder.withBody("这是Template消息2".getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId("消息ID:"+ UUID.randomUUID())
                .setContentEncoding("UTF-8")
//                .setHeader("desc","额外修改的信息描述")
//                .setHeader("info","请求头参数2")
                .build();


        Logger.info("发送信息");
        rabbitTemplate.send(ISSUE_EXCHANGE, ROUTER_KEY, message2);
    }
}
