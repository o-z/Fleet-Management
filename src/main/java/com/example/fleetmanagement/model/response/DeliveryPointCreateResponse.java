package com.example.fleetmanagement.model.response;

import com.example.fleetmanagement.model.enums.DeliveryPointEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPointCreateResponse {

    private Integer id;
    private DeliveryPointEnum type;
    private String name;
    private String location;
}
