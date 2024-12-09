package com.example.demostarter.config;

import com.example.demostarter.service.KafkaMessageSender;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaMessageSenderProperties.class)
public class KafkaMessageSenderAutoConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory(KafkaMessageSenderProperties properties) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(KafkaMessageSenderProperties properties) {
        return new KafkaTemplate<>(producerFactory(properties));
    }

    @Bean
    public KafkaMessageSender kafkaMessageSender(KafkaMessageSenderProperties properties, KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaMessageSender(properties, kafkaTemplate);
    }
}
