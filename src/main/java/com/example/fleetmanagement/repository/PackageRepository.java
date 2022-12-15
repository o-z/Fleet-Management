package com.example.fleetmanagement.repository;

import com.example.fleetmanagement.model.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {

    Set<PackageEntity> findAllByBarcodeIn(Set<String> barcodes);

    Optional<PackageEntity> findByBarcode(String barcode);

}