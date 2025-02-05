package com.wangkang.springkafkademo.kafkasender;

import com.wangkang.springkafkademo.po.Foo;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaSender {
    private final KafkaTemplate<String, Foo> kafkaTemplate;

    public void send(String foo, String topic) {
        kafkaTemplate.send(topic, new Foo(foo));
    }
}
