package com.wangkang.springkafkademo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ConsumerFailedToStartEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerFailedToStartListener {

    @EventListener
    public void handle(ConsumerFailedToStartEvent cse) {
        log.error("消费者启动失败事件:{}", cse);
    }
}
