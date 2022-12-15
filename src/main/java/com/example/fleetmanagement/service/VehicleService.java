package com.example.fleetmanagement.service;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import com.example.fleetmanagement.model.converter.VehicleConverter;
import com.example.fleetmanagement.model.entity.VehicleEntity;
import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
import com.example.fleetmanagement.model.request.VehicleRequest;
import com.example.fleetmanagement.model.response.VehicleResponse;
import com.example.fleetmanagement.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleConverter vehicleConverter;

    @Transactional
    public VehicleResponse create(final VehicleRequest vehicleRequest) {
        VehicleEntity savedVehicleEntity = vehicleRepository.save(VehicleEntity.builder()
                .plate(vehicleRequest.getPlate())
                .status(vehicleRequest.getStatus() != null ? vehicleRequest.getStatus() : VehicleStatusEnum.USABLE)
                .build());
        return vehicleConverter.convertFrom(savedVehicleEntity);

    }

    @Transactional
    public VehicleResponse statusUpdate(final VehicleRequest vehicleRequest) {
        VehicleEntity vehicleEntity = vehicleRepository.findByPlate(vehicleRequest.getPlate())
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.VEHICLE_NOT_FOUND_ERROR));
        vehicleEntity.setStatus(vehicleRequest.getStatus());
        vehicleRepository.save(vehicleEntity);
        return vehicleConverter.convertFrom(vehicleEntity);

    }

    @Transactional(readOnly = true)
    public VehicleResponse getByPlate(final String plate) {
        VehicleEntity vehicleEntity = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.VEHICLE_NOT_FOUND_ERROR));
        return vehicleConverter.convertFrom(vehicleEntity);

    }

    @Transactional(readOnly = true)
    public VehicleEntity getVehicleEntityByPlate(final String plate) {
        return vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.VEHICLE_NOT_FOUND_ERROR));
    }


}