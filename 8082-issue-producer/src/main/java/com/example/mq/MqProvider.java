package com.example.mq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

/**
 * 消息队列服务化（消息生产者）
 *
 * @author luyi
 */
@Service
public class MqProvider implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法
     * @param rabbitTemplate
     */
    public MqProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); // 设置 ack 回调
    }

    /**
     * MQ ack 机制
     * TODO 完善验证机制，确保消息能够被消费，且不影响消息吞吐量
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        Logger.info("MQMessage UUID: " + correlationData.getId());

        if (ack)
            Logger.info("MQMessage 消息消费成功！");
        else
            Logger.info("MQMessage 消息消费失败！");

        if (cause != null) {
            Logger.info("CallBackConfirm Cause: " + cause);
        }
    }
}
