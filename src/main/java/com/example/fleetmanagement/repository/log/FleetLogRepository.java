package com.example.fleetmanagement.repository.log;

import com.example.fleetmanagement.model.document.FleetLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FleetLogRepository extends MongoRepository<FleetLogDocument, UUID> {
}