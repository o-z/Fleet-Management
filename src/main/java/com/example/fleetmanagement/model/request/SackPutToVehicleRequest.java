package com.example.fleetmanagement.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class SackPutToVehicleRequest {
    @NotEmpty
    private Set<String> sackBarcodes;

    @NotEmpty
    private String plate;

}
