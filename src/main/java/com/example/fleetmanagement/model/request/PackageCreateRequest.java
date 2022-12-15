package com.example.fleetmanagement.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data

public class PackageCreateRequest {

    @NotNull
    private Integer desi;

    @NotNull
    private Integer deliveryPointId;
}
