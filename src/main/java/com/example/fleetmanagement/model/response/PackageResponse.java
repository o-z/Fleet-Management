package com.example.fleetmanagement.model.response;

import com.example.fleetmanagement.model.enums.FleetStateEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageResponse {

    private Long id;
    private String barcode;
    private FleetStateEnum state;
    private Integer desi;

}
