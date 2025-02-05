package com.wangkang.springkafkademo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerStoppedListener {

    @EventListener
    public void handle(ConsumerStoppedEvent cse) {
        log.info("ConsumerStoppedEvent:{}", cse);
    }
}
