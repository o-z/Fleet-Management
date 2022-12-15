package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.request.PackageCreateRequest;
import com.example.fleetmanagement.model.request.PackagePutToSackRequest;
import com.example.fleetmanagement.model.response.PackageResponse;
import com.example.fleetmanagement.service.PackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/packages")
@RequiredArgsConstructor
@Slf4j
public class PackageController {

    private final PackageService packageService;

    @PostMapping
    public ResponseEntity<PackageResponse> create(
            @Valid @RequestBody PackageCreateRequest request) {
        return ResponseEntity.ok(packageService.create(request));
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<PackageResponse> getByBarcode(
            @PathVariable("barcode") String barcode) {
        return ResponseEntity.ok(packageService.getByBarcode(barcode));
    }

    @PostMapping("/put-to-sack")
    public ResponseEntity putToSack(
            @Valid @RequestBody PackagePutToSackRequest request) {
        packageService.putToSack(request);
        return ResponseEntity.ok(null);
    }

}