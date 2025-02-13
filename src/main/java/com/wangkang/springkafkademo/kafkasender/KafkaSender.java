package com.wangkang.springkafkademo.kafkasender;

import com.wangkang.springkafkademo.po.Foo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaSender {
    private final KafkaTemplate<String, Foo> kafkaTemplate;

    public void send(String foo, String topic) {
        kafkaTemplate.send(topic, new Foo(foo))
                .handleAsync((t, ex) -> {
                    if (ex != null) {
                        //处理异常
                        log.error("发送失败了", ex);
                    } else {
                        log.info("发送成功了:{}", t);
                    }
                    return "ok";
                });
    }
}
