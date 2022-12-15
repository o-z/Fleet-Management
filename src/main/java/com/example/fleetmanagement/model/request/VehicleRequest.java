package com.example.fleetmanagement.model.request;

import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
@Builder
public class VehicleRequest {

    @NotEmpty
    private String plate;

    private VehicleStatusEnum status;

}
