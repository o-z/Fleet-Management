package com.example.fleetmanagement.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FleetStateEnum {
    CREATED(1),
    LOADED_INTO_SACK(2),
    LOADED(3),
    UNLOADED(4);

    private final Integer code;
}
