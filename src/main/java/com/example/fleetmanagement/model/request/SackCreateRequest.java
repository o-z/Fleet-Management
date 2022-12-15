package com.example.fleetmanagement.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class SackCreateRequest {

    @NotNull
    private Integer deliveryPointId;
}
