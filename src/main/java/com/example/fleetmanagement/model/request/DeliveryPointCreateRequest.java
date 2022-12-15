package com.example.fleetmanagement.model.request;


import com.example.fleetmanagement.model.enums.DeliveryPointEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class DeliveryPointCreateRequest {

    @NotNull
    private DeliveryPointEnum type;

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;
}
