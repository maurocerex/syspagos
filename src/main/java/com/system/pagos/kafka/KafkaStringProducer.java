package com.system.pagos.kafka;

import com.system.pagos.domain.PagosDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaStringProducer {


    private final KafkaTemplate<String, PagosDTO> kafkaTemplate;

    public KafkaStringProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, PagosDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PagosDTO message, String key) {
        log.info("Producing message {}", message);
        this.kafkaTemplate.send("TOPIC-DEMO", key, message);
    }

}