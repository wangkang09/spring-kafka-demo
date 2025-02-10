package com.wangkang.springkafkademo.controller;

import com.wangkang.springkafkademo.kafkasender.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    KafkaSender kafkaSender;

    @GetMapping("send")
    public String send(@RequestParam("topic") String topic, @RequestParam("msg") String msg) {
        kafkaSender.send(msg, topic);
        return "ok";
    }
}
