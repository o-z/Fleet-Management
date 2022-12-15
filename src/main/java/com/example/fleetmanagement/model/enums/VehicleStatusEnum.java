package com.example.fleetmanagement.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum VehicleStatusEnum {
    USABLE(null),
    ON_BRANCH(DeliveryPointEnum.BRANCH),
    ON_DISTRIBUTION_CENTRE(DeliveryPointEnum.DISTRIBUTION_CENTRE),
    ON_TRANSFER_CENTRE(DeliveryPointEnum.TRANSFER_CENTRE),
    ON_ROAD(null),
    PASSIVE(null),
    NON(null);

    private final DeliveryPointEnum deliveryPointEnum;

    public static final VehicleStatusEnum getByValue(DeliveryPointEnum deliveryPointEnum) {
        return Arrays.stream(VehicleStatusEnum.values()).filter(enumRole -> deliveryPointEnum.equals(enumRole.deliveryPointEnum)).findFirst().orElse(NON);
    }
}
