package com.example.fleetmanagement.model.converter;

import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import com.example.fleetmanagement.model.response.DeliveryPointCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class DeliveryPointConverter {

    public DeliveryPointCreateResponse convertFrom(final DeliveryPointEntity entity) {
        return DeliveryPointCreateResponse.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }
}
