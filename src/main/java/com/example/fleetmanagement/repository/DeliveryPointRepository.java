package com.example.fleetmanagement.repository;

import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPointRepository extends JpaRepository<DeliveryPointEntity, Integer> {
}