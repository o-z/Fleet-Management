package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.request.DistributeRequest;
import com.example.fleetmanagement.model.response.DistributeResponse;
import com.example.fleetmanagement.service.DistributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/distribute")
@RequiredArgsConstructor
@Slf4j
public class DistributeController {

    private final DistributeService distributeService;

    @PostMapping("/{vehiclePlate}")
    public ResponseEntity<DistributeResponse> distribute(
            @PathVariable("vehiclePlate") String vehiclePlate,
            @Valid @RequestBody DistributeRequest request) {
        return ResponseEntity.ok(distributeService.distribute(vehiclePlate, request));

    }

}