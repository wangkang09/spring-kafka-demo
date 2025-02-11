package com.wangkang.springkafkademo.inbound.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

/**
 * 用于消费者过滤消息的
 *
 */
@Slf4j
public class LoggingRecordFilterStrategy<K, V> implements RecordFilterStrategy<K, V> {

    @Override
    public boolean filter(ConsumerRecord<K, V> consumerRecord) {

        log.info("进入过滤器.topic:{},value:{},offset:{}", consumerRecord.topic(), consumerRecord.value(), consumerRecord.offset());
        return false;
    }
}
