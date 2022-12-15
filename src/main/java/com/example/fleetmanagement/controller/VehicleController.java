package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.request.VehicleRequest;
import com.example.fleetmanagement.model.response.VehicleResponse;
import com.example.fleetmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Valid @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.create(request));
    }

    @GetMapping("/{vehiclePlate}")
    public ResponseEntity<VehicleResponse> getByPlate(
            @PathVariable("vehiclePlate") String plate) {
        return ResponseEntity.ok(vehicleService.getByPlate(plate));
    }

    @PutMapping
    public ResponseEntity<VehicleResponse> statusUpdate(
            @Valid @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.statusUpdate(request));
    }
}