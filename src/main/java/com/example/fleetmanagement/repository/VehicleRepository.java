package com.example.fleetmanagement.repository;

import com.example.fleetmanagement.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {

    Optional<VehicleEntity> findByPlate(String plate);
}