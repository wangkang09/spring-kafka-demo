package com.wangkang.springkafkademo.kafkasender;

import com.wangkang.springkafkademo.po.Foo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;

@Slf4j
public class LoggingProducerInterceptor implements ProducerInterceptor<String, Foo> {
    private String servers;
    private Map<String, ?> configs;

    @Override
    public ProducerRecord<String, Foo> onSend(ProducerRecord<String, Foo> record) {
//        log.info("onSend:{},server:{},configs:{}", record, servers, configs);
        log.info("监听到发送消息.topic:{},value:{}", record.topic(), record.value());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("监听到提交:{}", metadata, exception);
    }

    @Override
    public void close() {
        log.info("关闭：LoggingProducerInterceptor");
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
