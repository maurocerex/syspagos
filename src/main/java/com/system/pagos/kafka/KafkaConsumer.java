package com.system.pagos.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "TOPIC-PAGOS" , groupId = "group_pagos")
    public void consume(String message) {
        log.info("Consuming Message {}", message);
    }

}