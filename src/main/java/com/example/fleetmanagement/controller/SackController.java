package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.request.SackCreateRequest;
import com.example.fleetmanagement.model.request.SackPutToVehicleRequest;
import com.example.fleetmanagement.model.response.SackResponse;
import com.example.fleetmanagement.service.SackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/sacks")
@RequiredArgsConstructor
@Slf4j
public class SackController {

    private final SackService sackService;

    @PostMapping
    public ResponseEntity<SackResponse> create(@Valid @RequestBody SackCreateRequest request) {
        return ResponseEntity.ok(sackService.create(request));
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<SackResponse> getByBarcode(@PathVariable("barcode") String barcode) {
        return ResponseEntity.ok(sackService.getByBarcode(barcode));
    }

    @PostMapping("/put-to-vehicle")
    public ResponseEntity putToVehicle(@Valid @RequestBody SackPutToVehicleRequest request) {
        sackService.putToVehicle(request);
        return ResponseEntity.ok(null);
    }

}