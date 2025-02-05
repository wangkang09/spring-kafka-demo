package com.wangkang.springkafkademo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ContainerStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContainerStoppedListener {

    @EventListener
    public void handle(ContainerStoppedEvent cse) {
        log.info("ContainerStoppedEvent:{}", cse);
    }
}
