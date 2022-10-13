package com.system.pagos.kafka;

import com.system.pagos.domain.PagosDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, PagosDto> kafkaTemplate;

    public KafkaProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, PagosDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PagosDto message, String key) {
        log.info("Producing message {}", message);
        this.kafkaTemplate.send("TOPIC-DEMO", key, message);
    }

}