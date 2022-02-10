package com.example.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过配置文件获取消息队列
 *
 * @author noodle
 */
@Configuration
public class RabbitConfig {

    @Value("${issue.exchange}")//交换机名
    public String ISSUE_EXCHANGE ;

    @Value("${issue.queue}")//消息队列名
    public String ISSUE_QUEUE ;

    @Value("${queue.key}")//消息队列名
    public String ROUTER_KEY;


    /*创建交换机*/
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(ISSUE_EXCHANGE,true,false);
    }

    /*创建队列*/
    @Bean
    public Queue directQueue1(){
        return new Queue(ISSUE_QUEUE,true);
    }

    /*创建绑定关系方法一*/
    @Bean
    public Binding directBind1(){
        return new Binding(ISSUE_QUEUE, Binding.DestinationType.QUEUE,
                ISSUE_EXCHANGE, ROUTER_KEY,null);
    }
}
