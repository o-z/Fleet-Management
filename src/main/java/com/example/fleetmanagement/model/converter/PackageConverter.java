package com.example.fleetmanagement.model.converter;

import com.example.fleetmanagement.model.entity.PackageEntity;
import com.example.fleetmanagement.model.response.PackageResponse;
import org.springframework.stereotype.Component;

@Component
public class PackageConverter {

    public PackageResponse convertFrom(final PackageEntity entity) {
        return PackageResponse.builder()
                .id(entity.getId())
                .barcode(entity.getBarcode())
                .state(entity.getState())
                .desi(entity.getDesi())
                .build();
    }
}
