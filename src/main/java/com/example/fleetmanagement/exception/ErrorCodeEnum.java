package com.example.fleetmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    INTERNAL_SERVER_ERROR(1000, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    FIELD_VALIDATION_ERROR(1001, "Field validation error.", HttpStatus.BAD_REQUEST),
    VEHICLE_NOT_FOUND_ERROR(1002, "Vehicle not found.", HttpStatus.BAD_REQUEST),
    SACK_NOT_FOUND_ERROR(1003, "Sack not found.", HttpStatus.BAD_REQUEST),
    PACKAGE_NOT_FOUND_ERROR(1004, "Package not found.", HttpStatus.BAD_REQUEST),
    DELIVERY_POINT_NOT_FOUND_ERROR(1005, "Delivery Point not found.", HttpStatus.BAD_REQUEST),
    BARCODE_NOT_VALID_ERROR(1006, "Barcode not valid.", HttpStatus.BAD_REQUEST),
    DELIVERY_POINT_NOT_MATCH_ERROR(1007, "Delivery Point not match.", HttpStatus.BAD_REQUEST),
    DISTRIBUTE_ERROR(1008, "Distribute error.", HttpStatus.BAD_REQUEST),
    VEHICLE_NOT_USABLE_ERROR(1009, "Vehicle not usable.", HttpStatus.BAD_REQUEST);


    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
