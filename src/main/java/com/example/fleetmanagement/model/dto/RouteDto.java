package com.example.fleetmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    @NotNull
    private Integer deliveryPoint;
    @NotEmpty
    private List<DeliveryDto> deliveries;
}
