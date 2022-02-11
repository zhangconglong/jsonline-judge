//package com.luyi;
//
//import com.example.mq.consumer.MqConsumer;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.tinylog.Logger;
//
//import java.nio.charset.StandardCharsets;
//import java.util.UUID;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class RabbitMqBootRabbitTemplateApplicationTests {
//
////    @Value("${}")//交换机名
//    public String ISSUE_EXCHANGE ="issue.exchange" ;
//
////    @Value("${issue.queue}")//消息队列名
//    public String ISSUE_QUEUE = "issue.queue" ;
//
////    @Value("${queue.key}")//消息队列名
//    public String ROUTER_KEY ="issue";
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    @Test
//    public void testTemplate(){
//
//      /*创建消息，可以指定消息具体参数*/
//      MessageProperties messageProperties = new MessageProperties();
//      messageProperties.getHeaders().put("desc","请求头desc参数信息描述");
//      messageProperties.getHeaders().put("type","请求头type参数信息描述");
//      messageProperties.setContentType("application/json");       //发送格式
//      messageProperties.setContentEncoding("UTF-8");              //UTF-8格式化
//
//      //封装消息
//      Message message = new Message("这是RabbitTemplate消息".getBytes(StandardCharsets.UTF_8), messageProperties);
//
//
//      /* MessagePostProcessor：发送消息前的消息拦截器
//       *    可以对消息参数进行修改，例如设置优先级、请求头等
//       */
////      rabbitTemplate.convertAndSend(ISSUE_EXCHANGE, ROUTER_KEY, message, new MessagePostProcessor() {
////            @Override
////            public Message postProcessMessage(Message message) throws AmqpException {
////              System.out.println("拦截需要发送的消息并进行二次设置");
////              message.getMessageProperties().getHeaders().put("desc","请求头desc参数信息修改");
////              message.getMessageProperties().getHeaders().put("attr","请求头额外新加attr参数");
////              return message;
////            }
////      });
//
//
//      /*创建消息，使用链式调用*/
//      Message message2 = MessageBuilder.withBody("这是Template消息2".getBytes(StandardCharsets.UTF_8))
//              .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//              .setMessageId("消息ID:"+ UUID.randomUUID())
//              .setContentEncoding("UTF-8")
//              .setHeader("desc","额外修改的信息描述")
//              .setHeader("info","请求头参数2")
//              .build();
////
////      rabbitTemplate.convertAndSend(ISSUE_EXCHANGE, "user.student", message2);
//
//
//      /*最简单的调用方式*/
//      Logger.info("发送信息");
//      rabbitTemplate.send(ISSUE_EXCHANGE, ROUTER_KEY, message2);
//
//    }
//
//    @Autowired
//    MqConsumer mqConsumer;
//
////    @Test
////    public void testReceiveIssue() throws Exception {
//////        MQMessage message = new MQMessage();
//////        Issue issue = new Issue();
//////        issue.setTitle("这是标题");
//////        issue.setLabel("Vue");
//////        message.setIssue(issue);
////
////
////        mqConsumer.receiveIssue();
////    }
//
//}
