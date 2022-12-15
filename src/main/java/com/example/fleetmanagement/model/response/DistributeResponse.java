package com.example.fleetmanagement.model.response;

import com.example.fleetmanagement.model.dto.RouteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributeResponse {
    private String vehicle;
    private List<RouteDto> route;

}
