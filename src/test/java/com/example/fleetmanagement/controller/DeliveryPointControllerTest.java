package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.enums.DeliveryPointEnum;
import com.example.fleetmanagement.model.request.DeliveryPointCreateRequest;
import com.example.fleetmanagement.model.response.DeliveryPointCreateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class DeliveryPointControllerTest extends BaseMySQLContainerTest {

    @Test
    public void testCreate() {
        DeliveryPointCreateRequest deliveryPointCreateRequest = new DeliveryPointCreateRequest();
        deliveryPointCreateRequest.setName("DISTRIBUTION_CENTRE_1");
        deliveryPointCreateRequest.setType(DeliveryPointEnum.DISTRIBUTION_CENTRE);
        deliveryPointCreateRequest.setLocation("24.61018,-61.72882");
        ResponseEntity<DeliveryPointCreateResponse> response = testRestTemplate.postForEntity("/v1/delivery-points", deliveryPointCreateRequest, DeliveryPointCreateResponse.class);
        DeliveryPointCreateResponse deliveryPointCreateResponse = response.getBody();

        assertNotNull(deliveryPointCreateResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4, deliveryPointCreateResponse.getId());
        assertEquals("DISTRIBUTION_CENTRE_1", deliveryPointCreateResponse.getName());
        assertEquals("24.61018,-61.72882", deliveryPointCreateResponse.getLocation());
        assertEquals(DeliveryPointEnum.DISTRIBUTION_CENTRE, deliveryPointCreateResponse.getType());


    }


}