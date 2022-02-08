//import com.luyi.MqApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * TestRabbitMQ
// * author: luyi
// * time: 2022/2/7 14:36
// */
//@SpringBootTest(classes = MqApplication.class)
//@RunWith(SpringRunner.class)
//public class TestRabbitMQ {
//    //注入RabbitMQ Template
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    //hello world
//    @Test
//    public void test1(){
//        rabbitTemplate.convertAndSend("hello", "hello world");
//    }
//}
