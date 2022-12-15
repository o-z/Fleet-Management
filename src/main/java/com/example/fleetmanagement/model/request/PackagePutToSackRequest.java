package com.example.fleetmanagement.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class PackagePutToSackRequest {
    @NotEmpty
    private Set<String> packageBarcodes;

    @NotEmpty
    private String sackBarcode;

}
