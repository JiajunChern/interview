package org.interview.springboot;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.interview.springboot.message.Producer;
import org.interview.springboot.message.RocketMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InterviewSpringbootApplicationTests {

    @Autowired
    private Producer producer;


    @Test
    void contextLoads() {
    }

    @Test
    void testRocketMQ() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        String str = "Hello World,My First RocketMQ Message=";
        Message message = new Message(RocketMQConfig.TOPIC, "tag1", str.getBytes());
        SendResult sendResult = producer.getProducer().send(message);
        System.out.println(sendResult);
    }

}
