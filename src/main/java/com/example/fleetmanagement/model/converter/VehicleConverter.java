package com.example.fleetmanagement.model.converter;

import com.example.fleetmanagement.model.entity.VehicleEntity;
import com.example.fleetmanagement.model.response.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleConverter {

    public VehicleResponse convertFrom(final VehicleEntity entity) {
        return VehicleResponse.builder()
                .id(entity.getId())
                .plate(entity.getPlate())
                .status(entity.getStatus())
                .build();
    }
}
