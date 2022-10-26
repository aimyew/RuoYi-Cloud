package com.ruoyi.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * kafka 消费者
 *
 * @date 2022/10/17 16:23
 */
@Component
@Slf4j
public class MyKfkConsumer {

    // kafka 的监听器 配置其 topic 和 消费者组
    @KafkaListener(topics = "default.specific.orderDetail", groupId = "dso1")
    public void dso1(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("dso1 topics=default.specific.orderDetail > groupId= >>>");
        log.info("dso1 record >> " + record);
        log.info("dso1 record.value() >> " + record.value());
        //手动提交offset
        ack.acknowledge();
    }

    // 配置多个消费组
    @KafkaListener(topics = "default.specific.orderDetail", groupId = "dso2")
    public void dso2(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("dso2 topics=default.specific.orderDetail > groupId=dso2 >>>");
        log.info("dso2 record >> " + record);
        log.info("dso2 record.value() >> " + record.value());
        ack.acknowledge();
    }
}
