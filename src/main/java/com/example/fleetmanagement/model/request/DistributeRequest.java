package com.example.fleetmanagement.model.request;

import com.example.fleetmanagement.model.dto.RouteDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class DistributeRequest {

    @NotEmpty
    private List<RouteDto> route;
}
