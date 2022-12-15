package com.example.fleetmanagement.model.enums;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public enum FleetTypeEnum {
    SACK(Pattern.compile("^C"), "C"),
    PACKAGE(Pattern.compile("^P"), "P");

    private final Pattern regex;
    private final String prefix;

    public static FleetTypeEnum resolve(String barcode) {
        return Arrays.stream(FleetTypeEnum.values())
                .filter(fleetTypeEnum -> fleetTypeEnum.getRegex().matcher(barcode).find())
                .findFirst()
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.BARCODE_NOT_VALID_ERROR));
    }
}
