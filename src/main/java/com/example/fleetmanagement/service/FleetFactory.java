package com.example.fleetmanagement.service;

import com.example.fleetmanagement.model.dto.DeliveryDto;
import com.example.fleetmanagement.model.enums.FleetTypeEnum;

public interface FleetFactory {
    DeliveryDto stateChangeByConditional(final String barcode, final Integer desiredDeliveryPoint);

    FleetTypeEnum getType();

}
