package org.interview.springboot.controller;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.interview.springboot.message.RocketMQConfig;
import org.interview.springboot.message.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Producer producer;

    @GetMapping(value = "/test/{msg}")
    public Object test(@PathVariable String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String str = "Hello World,My First RocketMQ Message=" + msg;
        Message message = new Message(RocketMQConfig.TOPIC, "tag1", str.getBytes());
        SendResult sendResult = producer.getProducer().send(message);
        System.out.println(sendResult);
        return sendResult.toString();
    }
}
