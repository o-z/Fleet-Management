package com.example.fleetmanagement.kafka.producer;

import com.example.fleetmanagement.model.dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FleetLogProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String fleetLogTopic;

    public void sendMessage(final LogDto logDto) {
        kafkaTemplate.send(fleetLogTopic, logDto);
    }
}