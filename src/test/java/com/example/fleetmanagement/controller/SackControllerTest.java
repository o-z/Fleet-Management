package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.enums.FleetStateEnum;
import com.example.fleetmanagement.model.request.SackCreateRequest;
import com.example.fleetmanagement.model.request.SackPutToVehicleRequest;
import com.example.fleetmanagement.model.response.SackResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SackControllerTest extends BaseMySQLContainerTest {

    @Test
    public void testCreate() {
        SackCreateRequest sackCreateRequest = new SackCreateRequest();
        sackCreateRequest.setDeliveryPointId(1);
        ResponseEntity<SackResponse> response = testRestTemplate.postForEntity("/v1/sacks", sackCreateRequest, SackResponse.class);
        SackResponse sackResponse = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(sackResponse);
        assertEquals(3, sackResponse.getId());
        assertEquals("C3", sackResponse.getBarcode());
        assertEquals(FleetStateEnum.CREATED, sackResponse.getState());

    }

    @Test
    public void testGetByBarcode() {
        ResponseEntity<SackResponse> response = testRestTemplate.getForEntity("/v1/sacks/{barcode}", SackResponse.class, "C725799");
        SackResponse sackResponse = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(sackResponse);
        assertEquals(1, sackResponse.getId());
        assertEquals("C725799", sackResponse.getBarcode());
        assertEquals(FleetStateEnum.LOADED, sackResponse.getState());

    }

    @Test
    public void testPutToVehicle() {
        SackPutToVehicleRequest sackPutToVehicleRequest = new SackPutToVehicleRequest();
        sackPutToVehicleRequest.setSackBarcodes(Set.of("C725799"));
        sackPutToVehicleRequest.setPlate("34TL34");
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/v1/sacks/put-to-vehicle", sackPutToVehicleRequest, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}