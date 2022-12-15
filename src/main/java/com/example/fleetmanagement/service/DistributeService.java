package com.example.fleetmanagement.service;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import com.example.fleetmanagement.model.dto.DeliveryDto;
import com.example.fleetmanagement.model.dto.RouteDto;
import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import com.example.fleetmanagement.model.entity.VehicleEntity;
import com.example.fleetmanagement.model.enums.FleetTypeEnum;
import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
import com.example.fleetmanagement.model.request.DistributeRequest;
import com.example.fleetmanagement.model.request.VehicleRequest;
import com.example.fleetmanagement.model.response.DistributeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DistributeService {
    private final List<FleetFactory> fleetFactories;
    private final VehicleService vehicleService;
    private final DeliveryPointService deliveryPointService;

    @CircuitBreaker(name = "distributeCB", fallbackMethod = "getDistributeBack")
    @Transactional
    public DistributeResponse distribute(final String vehiclePlate, final DistributeRequest distributeRequest) {
        VehicleEntity vehicleEntityByPlate = vehicleService.getVehicleEntityByPlate(vehiclePlate);
        if (vehicleEntityByPlate.getStatus() != VehicleStatusEnum.USABLE) {
            throw new FleetManagementException(ErrorCodeEnum.VEHICLE_NOT_USABLE_ERROR);
        }
        DistributeResponse distributeResponse = new DistributeResponse();
        distributeResponse.setVehicle(vehiclePlate);

        List<RouteDto> responseRoutes = distributeRoute(distributeRequest, vehicleEntityByPlate);

        vehicleService.statusUpdate(VehicleRequest.builder()
                .plate(vehicleEntityByPlate.getPlate())
                .status(VehicleStatusEnum.USABLE)
                .build());
        distributeResponse.setRoute(responseRoutes);
        return distributeResponse;
    }

    private List<RouteDto> distributeRoute(final DistributeRequest distributeRequest, final VehicleEntity vehicleEntityByPlate) {
        List<RouteDto> responseRoutes = new ArrayList<>();
        distributeRequest.getRoute().forEach(route -> {
            RouteDto responseRoute = new RouteDto();
            responseRoute.setDeliveryPoint(route.getDeliveryPoint());
            List<DeliveryDto> deliveryDtos = new ArrayList<>();
            vehicleToDistribution(vehicleEntityByPlate, route.getDeliveryPoint());
            route.getDeliveries().forEach(delivery -> {
                fleetFactories.forEach(fleetFactory -> {
                    if (fleetFactory.getType() == FleetTypeEnum.resolve(delivery.getBarcode())) {
                        deliveryDtos.add(fleetFactory.stateChangeByConditional(delivery.getBarcode(),
                                route.getDeliveryPoint()));
                    }
                });
                responseRoute.setDeliveries(deliveryDtos);
            });
            responseRoutes.add(responseRoute);
        });
        return responseRoutes;
    }

    private void vehicleToDistribution(final VehicleEntity vehicleEntityByPlate, final Integer deliveryPointId) {
        DeliveryPointEntity deliveryPointEntity = deliveryPointService.getById(deliveryPointId);
        if (!vehicleEntityByPlate.getStatus().equals(VehicleStatusEnum.getByValue(deliveryPointEntity.getType()))) {
            vehicleService.statusUpdate(VehicleRequest.builder()
                    .plate(vehicleEntityByPlate.getPlate())
                    .status(VehicleStatusEnum.getByValue(deliveryPointEntity.getType()))
                    .build());
        }
    }

    private DistributeResponse getDistributeBack(Exception e) throws Exception {
        log.error("Distribute error : ", e);
        if (e instanceof FleetManagementException) {
            throw e;
        }
        throw new FleetManagementException(ErrorCodeEnum.DISTRIBUTE_ERROR);
    }

}