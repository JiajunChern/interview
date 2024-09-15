package org.interview.springboot.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {
    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "consumer_group";

    public Consumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(RocketMQConfig.TOPIC, "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    Message message = msgs.get(0);
                    System.out.println("RocketMQ消费者收到消息，当前线程名称：" + Thread.currentThread().getName() + "，收到的消息是：" + new String(message.getBody()));

                    String topic = message.getTopic();
                    String tags = message.getTags();
                    String keys = message.getKeys();
                    String body = new String(message.getBody(), "utf-8");

                    System.out.println("消息主题是：" + topic + "，标签是：" + tags + "消息唯一标识是：" + keys + "消息内容：" + body);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;

                }
            }
        });

        consumer.start();
        System.out.println("消费者启动");
    }
}
