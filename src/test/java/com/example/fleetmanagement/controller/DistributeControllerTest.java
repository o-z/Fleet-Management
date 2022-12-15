package com.example.fleetmanagement.controller;

import com.example.fleetmanagement.model.dto.DeliveryDto;
import com.example.fleetmanagement.model.dto.RouteDto;
import com.example.fleetmanagement.model.request.DistributeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"}, topics = "fleet-log")
class DistributeControllerTest extends BaseMySQLContainerTest {


    @Test
    public void testDistribute() {

        DistributeRequest distributeRequest = new DistributeRequest();
        List<RouteDto> routeDtos = new ArrayList<>();
        RouteDto routeDto1 = new RouteDto();
        routeDto1.setDeliveryPoint(1);
        List<DeliveryDto> deliveryDtos1 = new ArrayList<>();
        DeliveryDto deliveryDto1 = DeliveryDto.builder().barcode("P7988000121").build();
        deliveryDtos1.add(deliveryDto1);
        DeliveryDto deliveryDto2 = DeliveryDto.builder().barcode("P7988000122").build();
        deliveryDtos1.add(deliveryDto2);
        DeliveryDto deliveryDto3 = DeliveryDto.builder().barcode("P7988000123").build();
        deliveryDtos1.add(deliveryDto3);
        DeliveryDto deliveryDto4 = DeliveryDto.builder().barcode("P8988000121").build();
        deliveryDtos1.add(deliveryDto4);
        DeliveryDto deliveryDto5 = DeliveryDto.builder().barcode("C725799").build();
        deliveryDtos1.add(deliveryDto5);
        routeDto1.setDeliveries(deliveryDtos1);
        routeDtos.add(routeDto1);


        RouteDto routeDto2 = new RouteDto();
        routeDto2.setDeliveryPoint(2);
        List<DeliveryDto> deliveryDtos2 = new ArrayList<>();
        DeliveryDto deliveryDto6 = DeliveryDto.builder().barcode("P8988000123").build();
        deliveryDtos2.add(deliveryDto6);
        DeliveryDto deliveryDto7 = DeliveryDto.builder().barcode("P8988000124").build();
        deliveryDtos2.add(deliveryDto7);
        DeliveryDto deliveryDto8 = DeliveryDto.builder().barcode("P8988000125").build();
        deliveryDtos2.add(deliveryDto8);
        DeliveryDto deliveryDto9 = DeliveryDto.builder().barcode("C725799").build();
        deliveryDtos2.add(deliveryDto9);
        routeDto2.setDeliveries(deliveryDtos2);
        routeDtos.add(routeDto2);


        RouteDto routeDto3 = new RouteDto();
        routeDto3.setDeliveryPoint(2);
        List<DeliveryDto> deliveryDtos3 = new ArrayList<>();
        DeliveryDto deliveryDto10 = DeliveryDto.builder().barcode("P9988000126").build();
        deliveryDtos3.add(deliveryDto10);
        DeliveryDto deliveryDto11 = DeliveryDto.builder().barcode("P9988000127").build();
        deliveryDtos3.add(deliveryDto11);
        DeliveryDto deliveryDto12 = DeliveryDto.builder().barcode("P9988000128").build();
        deliveryDtos3.add(deliveryDto12);
        DeliveryDto deliveryDto13 = DeliveryDto.builder().barcode("P9988000129").build();
        deliveryDtos3.add(deliveryDto13);
        DeliveryDto deliveryDto14 = DeliveryDto.builder().barcode("P9988000130").build();
        deliveryDtos3.add(deliveryDto14);
        routeDto3.setDeliveries(deliveryDtos3);
        routeDtos.add(routeDto3);


        distributeRequest.setRoute(routeDtos);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/v1/distribute/{vehiclePlate}", distributeRequest, String.class, "34TL34");
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String successResponse = "{\"vehicle\":\"34TL34\",\"route\":[{\"deliveryPoint\":1,\"deliveries\":[{\"barcode\":" +
                "\"P7988000121\",\"state\":4},{\"barcode\":\"P7988000122\",\"state\":4},{\"barcode\":\"P7988000123\"," +
                "\"state\":4},{\"barcode\":\"P8988000121\",\"state\":3},{\"barcode\":\"C725799\",\"state\":3}]}," +
                "{\"deliveryPoint\":2,\"deliveries\":[{\"barcode\":\"P8988000123\",\"state\":4},{\"barcode\":\"P8988000124\"," +
                "\"state\":4},{\"barcode\":\"P8988000125\",\"state\":4},{\"barcode\":\"C725799\",\"state\":4}]},{\"deliveryPoint\":2," +
                "\"deliveries\":[{\"barcode\":\"P9988000126\",\"state\":3},{\"barcode\":\"P9988000127\",\"state\":3}," +
                "{\"barcode\":\"P9988000128\",\"state\":3},{\"barcode\":\"P9988000129\",\"state\":3},{\"barcode\":\"P9988000130\"," +
                "\"state\":3}]}]}";
        assertEquals(response.getBody(), successResponse);
    }
}