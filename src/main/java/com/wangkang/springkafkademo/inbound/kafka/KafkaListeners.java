package com.wangkang.springkafkademo.inbound.kafka;

import com.wangkang.springkafkademo.po.Foo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * 有可能会消费多个kafka地址，所以直接针对这种情况做配置
 */
@Configuration
@Slf4j
public class KafkaListeners {

    /**
     * properties 中配置url，也可以配置其它的，可以用 spel 表达式
     * @param message
     * @param acknowledgment 只有在设置为手动提交时才能声明，不然会报错
     */
    @KafkaListener(groupId = "ots1", topics = "my-secure-topic")
    public void listenOts(ConsumerRecord<String, Foo> message, Acknowledgment acknowledgment) {
        log.info("Received. topic:{},value:{},offset:{}", message.topic(), message.value(), message.offset());
        if (message.value().getFoo().startsWith("fail")) {
            throw new RuntimeException("failed");
        }
        acknowledgment.acknowledge();
    }

//    /**
//     * @param message
//     * @param acknowledgment 只有在设置为手动提交时才能声明，不然会报错
//     */
//    @KafkaListener(groupId = "oqs1", topics = "topic2", properties = "bootstrap.servers=${spring.kafka.consumer.oqs.bootstrap-servers}")
//    public void listenOqs(ConsumerRecord<String, Foo> message, Acknowledgment acknowledgment) {
//        log.info("Received. topic:{},value:{},offset:{}", message.topic(), message.value(), message.offset());
//        if (message.value().getFoo().startsWith("fail")) {
//            throw new RuntimeException("failed");
//        }
//        acknowledgment.acknowledge();
//    }
}
