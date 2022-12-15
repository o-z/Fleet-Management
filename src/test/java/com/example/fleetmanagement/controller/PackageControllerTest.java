package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.enums.FleetStateEnum;
import com.example.fleetmanagement.model.request.PackageCreateRequest;
import com.example.fleetmanagement.model.request.PackagePutToSackRequest;
import com.example.fleetmanagement.model.response.PackageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackageControllerTest extends BaseMySQLContainerTest {

    @Test
    public void testCreate() {
        PackageCreateRequest packageCreateRequest = new PackageCreateRequest();
        packageCreateRequest.setDesi(20);
        packageCreateRequest.setDeliveryPointId(1);
        ResponseEntity<PackageResponse> response = testRestTemplate.postForEntity("/v1/packages", packageCreateRequest, PackageResponse.class);
        PackageResponse packageResponse = response.getBody();

        assertNotNull(packageResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(16, packageResponse.getId());
        assertEquals("P16", packageResponse.getBarcode());
        assertEquals(FleetStateEnum.CREATED, packageResponse.getState());
        assertEquals(20, packageResponse.getDesi());

    }

    @Test
    public void testGetByBarcode() {
        ResponseEntity<PackageResponse> response = testRestTemplate.getForEntity("/v1/packages/{barcode}", PackageResponse.class, "P7988000121");
        PackageResponse packageResponse = response.getBody();

        assertNotNull(packageResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, packageResponse.getId());
        assertEquals("P7988000121", packageResponse.getBarcode());
        assertEquals(FleetStateEnum.CREATED, packageResponse.getState());
        assertEquals(5, packageResponse.getDesi());

    }

    @Test
    public void testPutToSack() {
        PackagePutToSackRequest packagePutToSackRequest = new PackagePutToSackRequest();
        packagePutToSackRequest.setPackageBarcodes(Set.of("P8988000125"));
        packagePutToSackRequest.setSackBarcode("C725799");
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/v1/packages/put-to-sack", packagePutToSackRequest, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}