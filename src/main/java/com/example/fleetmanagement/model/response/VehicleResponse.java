package com.example.fleetmanagement.model.response;

import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Integer id;
    private String plate;
    private VehicleStatusEnum status;
}
