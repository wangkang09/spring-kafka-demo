package com.wangkang.springkafkademo.inbound.kafka;

import com.wangkang.springkafkademo.po.Foo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;

@Slf4j
public class LoggingConsumerInterceptor implements ConsumerInterceptor<String, Foo> {
    private String servers;
    private Map<String, ?> configs;

    @Override
    public ConsumerRecords<String, Foo> onConsume(ConsumerRecords<String, Foo> records) {
        records.forEach(record -> {
            log.info("onConsume.topic:{},value:{},offset:{},servers:{}", record.topic(), record.value(), record.offset(),servers);
        });
        return records;
    }

    @Override
    public void close() {
        log.info("LoggingConsumerInterceptor close");
    }

    @Override
    public void onCommit(Map offsets) {
        log.info("onCommit:{}", offsets);
    }

    @Override
    public void configure(Map<String, ?> configs) {
        try {
            this.configs = configs;
            servers = configs.get(BOOTSTRAP_SERVERS_CONFIG).toString();
        } catch (Exception e) {
            servers = "unknown";
        }
    }
}
