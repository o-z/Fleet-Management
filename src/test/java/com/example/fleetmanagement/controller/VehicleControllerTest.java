package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
import com.example.fleetmanagement.model.request.VehicleRequest;
import com.example.fleetmanagement.model.response.VehicleResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleControllerTest extends BaseMySQLContainerTest {

    @Test
    @Order(1)
    public void testCreate() {
        VehicleRequest vehicleRequest = VehicleRequest.builder().plate("12TL12").status(VehicleStatusEnum.USABLE).build();
        ResponseEntity<VehicleResponse> response = testRestTemplate.postForEntity("/v1/vehicles", vehicleRequest, VehicleResponse.class);
        VehicleResponse vehicleResponse = response.getBody();

        assertNotNull(vehicleResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, vehicleResponse.getId());
        assertEquals("12TL12", vehicleResponse.getPlate());
        assertEquals(VehicleStatusEnum.USABLE, vehicleResponse.getStatus());

    }

    @Test
    @Order(2)
    public void testGetByPlate() {
        ResponseEntity<VehicleResponse> response = testRestTemplate.getForEntity("/v1/vehicles/{vehiclePlate}", VehicleResponse.class, "34Tl34");
        VehicleResponse vehicleResponse = response.getBody();

        assertNotNull(vehicleResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, vehicleResponse.getId());
        assertEquals("34TL34", vehicleResponse.getPlate());
        assertEquals(VehicleStatusEnum.USABLE, vehicleResponse.getStatus());

    }

    @Test
    @Order(3)
    public void testStatusUpdate() {
        VehicleRequest vehicleRequest = VehicleRequest.builder().plate("34TL34").status(VehicleStatusEnum.ON_BRANCH).build();
        HttpEntity<VehicleRequest> requestEntity = new HttpEntity<>(vehicleRequest, null);

        ResponseEntity<VehicleResponse> response = testRestTemplate.exchange("/v1/vehicles", HttpMethod.PUT, requestEntity, VehicleResponse.class, new HashMap<>());
        VehicleResponse vehicleResponse = response.getBody();

        assertNotNull(vehicleResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, vehicleResponse.getId());
        assertEquals("34TL34", vehicleResponse.getPlate());
        assertEquals(VehicleStatusEnum.ON_BRANCH, vehicleResponse.getStatus());

    }
}
