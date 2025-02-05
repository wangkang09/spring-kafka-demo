package com.wangkang.springkafkademo.inbound.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;

import java.util.Collection;

@Slf4j
public class LoggingConsumerAwareRebalanceListener implements ConsumerAwareRebalanceListener {

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        log.info("onPartitionsRevoked:{}", partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        log.info("onPartitionsAssigned:{}", partitions);
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        log.info("onPartitionsLost:{}", partitions);
    }
}
