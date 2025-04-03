package com.alejandrosanchez.client.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientStatusProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClientStatusProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClientStatusUpdate(Long clientId, boolean active) {
        String message = clientId + "," + active;
        kafkaTemplate.send("client-status-topic", message);
    }
}
