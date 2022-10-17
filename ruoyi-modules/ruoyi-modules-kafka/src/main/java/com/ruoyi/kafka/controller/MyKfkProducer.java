package com.ruoyi.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * kafka 生产者
 *
 * @date 2022/10/17 16:23
 */
@RestController
public class MyKfkProducer {
    private final static String TOPIC_NAME = "default.specific.orderDetail"; // topic 的名称

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send1")
    public void send1() throws ExecutionException, InterruptedException {
        // 同步发送
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, "send_key_1", "send_value_1");
        SendResult<String, String> stringStringSendResult = future.get();
        System.out.println("send1 stringStringSendResult >> " + stringStringSendResult);
    }

    @RequestMapping("/send2")
    public void send2() {
        // 异步发送回调
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, "send_key_2", "send_value_2");
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("send2 " + result.getProducerRecord());
                System.out.println("send2 " + result.getRecordMetadata());
                System.out.println("send2 offset " + result.getRecordMetadata().offset());
            }
        });
    }
}