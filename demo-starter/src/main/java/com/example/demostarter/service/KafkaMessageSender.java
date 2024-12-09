package com.example.demostarter.service;

import com.example.demostarter.config.KafkaMessageSenderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaMessageSenderProperties properties;

    @Autowired
    public KafkaMessageSender(KafkaMessageSenderProperties properties, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(properties.getTopic(), message);
    }

    public String getTopic(){
        return properties.getTopic();
    }

}
