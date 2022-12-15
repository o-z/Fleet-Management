package com.example.fleetmanagement.repository;

import com.example.fleetmanagement.model.entity.SackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SackRepository extends JpaRepository<SackEntity, Long> {
    Optional<SackEntity> findByBarcode(String barcode);

    Set<SackEntity> findAllByBarcodeIn(Set<String> barcodes);
}