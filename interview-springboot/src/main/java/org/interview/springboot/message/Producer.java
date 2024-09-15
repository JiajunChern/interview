package org.interview.springboot.message;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;


@Component
public class Producer {
    private String producerGroup = "producer_group";

    private DefaultMQProducer producer;

    public Producer() {
        producer = new DefaultMQProducer(producerGroup);
        //指定nameserver地址，多个地址使用,分隔
        producer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        producer.setVipChannelEnabled(false);
        start();
    }

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        this.producer.shutdown();
    }
}
