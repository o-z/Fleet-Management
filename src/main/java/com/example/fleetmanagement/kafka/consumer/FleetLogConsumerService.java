package com.example.fleetmanagement.kafka.consumer;

import com.example.fleetmanagement.model.dto.LogDto;
import com.example.fleetmanagement.service.log.FleetLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FleetLogConsumerService {

    private final FleetLogService fleetLogService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaLogContainerFactory")
    public void listen(@Payload final LogDto logDto) {
        fleetLogService.saveLog(logDto);
    }
}