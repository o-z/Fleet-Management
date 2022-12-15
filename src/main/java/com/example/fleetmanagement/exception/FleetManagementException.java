package com.example.fleetmanagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FleetManagementException extends RuntimeException {

    private final Integer code;
    private final String message;
    private final HttpStatus status;

    public FleetManagementException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        this.status = errorCodeEnum.getHttpStatus();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
