package com.wangkang.springkafkademo.kafkasender;

import com.wangkang.springkafkademo.po.Foo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * 使用 springboot 内置的就行 org.springframework.kafka.support.LoggingProducerListener
 *
 */
@Slf4j
public class LoggingProducerListener implements ProducerListener<String, Foo> {
    @Override
    public void onSuccess(ProducerRecord<String, Foo> producerRecord, RecordMetadata recordMetadata) {
    }

    @Override
    public void onError(ProducerRecord<String, Foo> producerRecord, RecordMetadata recordMetadata, Exception exception) {
    }
}
