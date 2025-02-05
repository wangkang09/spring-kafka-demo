package com.wangkang.springkafkademo.inbound.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.RecordInterceptor;

@Slf4j
public class LoggingRecordInterceptor<K, V> implements RecordInterceptor<K, V> {

    @Override
    public ConsumerRecord<K, V> intercept(ConsumerRecord<K, V> record, Consumer<K, V> consumer) {
        log.info("intercept.topic:{},value:{},offset:{},consumer:{}", record.topic(), record.value(), record.offset(), consumer);
        return record;
    }

    @Override
    public void success(ConsumerRecord<K, V> record, Consumer<K, V> consumer) {
        log.info("success.topic:{},value:{},offset:{},consumer:{}", record.topic(), record.value(), record.offset(), consumer);
    }

    @Override
    public void failure(ConsumerRecord<K, V> record, Exception exception, Consumer<K, V> consumer) {
        log.info("failure.topic:{},value:{},offset:{},consumer:{}", record.topic(), record.value(), record.offset(), consumer, exception);
    }
}
