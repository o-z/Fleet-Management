package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.request.DeliveryPointCreateRequest;
import com.example.fleetmanagement.model.response.DeliveryPointCreateResponse;
import com.example.fleetmanagement.service.DeliveryPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/delivery-points")
@RequiredArgsConstructor
@Slf4j
public class DeliveryPointController {

    private final DeliveryPointService deliveryPointService;

    @PostMapping
    public ResponseEntity<DeliveryPointCreateResponse> create(
            @Valid @RequestBody DeliveryPointCreateRequest request) {
        return ResponseEntity.ok(deliveryPointService.create(request));
    }
}
