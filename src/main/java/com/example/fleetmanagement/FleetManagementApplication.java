package com.example.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
@EnableMongoAuditing
public class FleetManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetManagementApplication.class, args);
    }

}


