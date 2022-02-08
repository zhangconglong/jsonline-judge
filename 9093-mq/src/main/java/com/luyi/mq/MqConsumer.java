package com.luyi.mq;

import com.example.common.pojo.Issue;
import com.example.mq.vo.MQMessage;
import com.seckill.dis.common.api.cache.vo.OrderKeyPrefix;
import com.seckill.dis.common.api.goods.GoodsServiceApi;
import com.seckill.dis.common.api.goods.vo.GoodsVo;
import com.seckill.dis.common.domain.SeckillOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

/**
 * MQ消息接收者, 消费者
 * 消费者绑定在队列监听，既可以接收到队列中的消息
 *
 * @author noodle
 */
@Service
public class MqConsumer {

    @Autowired
     IssueESService issueESService;

    /**
     * 插入ES
     *
     * @param message
     */
    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receiveSkInfo(MQMessage message) {
        Logger.info("RabbitMQ收到消息: " + message);

        Issue issue = message.getIssue();// 获取题目

        issueESService.insertESIssue(issue);// 写入ES
    }

}
