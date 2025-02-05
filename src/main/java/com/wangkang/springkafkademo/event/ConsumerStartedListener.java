package com.wangkang.springkafkademo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ConsumerStartedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerStartedListener {

    @EventListener
    public void handle(ConsumerStartedEvent cse) {
        log.info("ConsumerStartedEvent:{}", cse);
    }
}
