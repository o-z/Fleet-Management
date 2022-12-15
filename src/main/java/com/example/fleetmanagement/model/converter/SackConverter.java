package com.example.fleetmanagement.model.converter;

import com.example.fleetmanagement.model.entity.SackEntity;
import com.example.fleetmanagement.model.response.SackResponse;
import org.springframework.stereotype.Component;

@Component
public class SackConverter {

    public SackResponse convertFrom(final SackEntity entity) {
        return SackResponse.builder()
                .id(entity.getId())
                .barcode(entity.getBarcode())
                .state(entity.getState())
                .build();
    }
}
