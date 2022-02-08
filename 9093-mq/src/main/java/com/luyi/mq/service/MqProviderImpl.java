package com.luyi.mq.service;

import com.example.mq.MqProviderApi;
import com.example.mq.vo.MQMessage;
import com.luyi.mq.MQConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.UUID;

/**
 * 消息队列服务化（消息生产者）
 *
 * @author noodle
 */
@Service(interfaceClass = MqProviderApi.class)
public class MqProviderImpl implements MqProviderApi, RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法
     * @param rabbitTemplate
     */
    @Autowired
    public MqProviderImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); // 设置 ack 回调
    }

    @Override
    public void sendMQMessage(MQMessage message) {
        Logger.info("MQ发送消息: " + message);
        // 秒杀消息 关联的数据
        CorrelationData skCorrData = new CorrelationData(UUID.randomUUID().toString());
        // （消息队列名，发送的消息）
        rabbitTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, message, skCorrData);
    }

    /**
     * MQ ack 机制
     * TODO 完善验证机制，确保消息能够被消费，且不影响消息吞吐量
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        Logger.info("MQMessage UUID: " + correlationData.getId());
        if (ack) {
            Logger.info("MQMessage 消息消费成功！");
        } else {
            System.out.println("MQMessage 消息消费失败！");
        }

        if (cause != null) {
            Logger.info("CallBackConfirm Cause: " + cause);
        }
    }
}
