package com.example.fleetmanagement.service;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import com.example.fleetmanagement.kafka.producer.FleetLogProducerService;
import com.example.fleetmanagement.model.converter.SackConverter;
import com.example.fleetmanagement.model.dto.DeliveryDto;
import com.example.fleetmanagement.model.dto.LogDto;
import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import com.example.fleetmanagement.model.entity.SackEntity;
import com.example.fleetmanagement.model.entity.VehicleEntity;
import com.example.fleetmanagement.model.enums.FleetStateEnum;
import com.example.fleetmanagement.model.enums.FleetTypeEnum;
import com.example.fleetmanagement.model.request.SackCreateRequest;
import com.example.fleetmanagement.model.request.SackPutToVehicleRequest;
import com.example.fleetmanagement.model.response.SackResponse;
import com.example.fleetmanagement.repository.SackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SackService implements FleetFactory {
    private final SackRepository sackRepository;
    private final SackConverter sackConverter;
    private final VehicleService vehicleService;
    private final DeliveryPointService deliveryPointService;
    private final FleetLogProducerService fleetLogProducerService;

    @Transactional(readOnly = true)
    public SackEntity getSackEntityByBarcode(final String barcode) {
        return sackRepository.findByBarcode(barcode)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.SACK_NOT_FOUND_ERROR));
    }

    @Transactional
    public SackResponse create(final SackCreateRequest request) {
        DeliveryPointEntity deliveryPointEntity = deliveryPointService.getById(request.getDeliveryPointId());
        SackEntity savedSackEntity = sackRepository.save(SackEntity.builder()
                .state(FleetStateEnum.CREATED)
                .deliveryPointEntity(deliveryPointEntity)
                .build());
        savedSackEntity.setBarcode(FleetTypeEnum.SACK.getPrefix() + savedSackEntity.getId());
        sackRepository.save(savedSackEntity);
        return sackConverter.convertFrom(savedSackEntity);
    }

    @Transactional(readOnly = true)
    public SackResponse getByBarcode(final String barcode) {
        SackEntity sackEntity = getSackEntityByBarcode(barcode);
        return sackConverter.convertFrom(sackEntity);
    }

    @Transactional
    public void putToVehicle(final SackPutToVehicleRequest request) {
        VehicleEntity vehicleEntity = vehicleService.getVehicleEntityByPlate(request.getPlate());
        Set<SackEntity> sackEntities = sackRepository.findAllByBarcodeIn(request.getSackBarcodes());
        sackEntities.forEach(sackEntity -> {
            sackEntity.setVehicleEntity(vehicleEntity);
            sackEntity.setState(FleetStateEnum.LOADED);
        });
        sackRepository.saveAll(sackEntities);
    }

    @Transactional
    @Override
    public DeliveryDto stateChangeByConditional(final String barcode, final Integer desiredDeliveryPoint) {
        SackEntity sackEntity = sackRepository.findByBarcode(barcode)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.SACK_NOT_FOUND_ERROR));
        sackEntity.getPackageEntities().forEach(packageEntity -> {
            if (desiredDeliveryPoint.equals(packageEntity.getDeliveryPointEntity().getId())) {
                packageEntity.setState(FleetStateEnum.UNLOADED);
            } else {
                packageEntity.setState(FleetStateEnum.LOADED);
                fleetLogProducerService.sendMessage(LogDto.builder()
                        .barcode(barcode)
                        .desiredDeliveryPoint(desiredDeliveryPoint)
                        .realDeliveryPoint(sackEntity.getDeliveryPointEntity().getId())
                        .build());
            }
        });
        if (desiredDeliveryPoint.equals(sackEntity.getDeliveryPointEntity().getId())) {
            sackEntity.setState(FleetStateEnum.UNLOADED);
        } else {
            sackEntity.setState(FleetStateEnum.LOADED);

        }
        sackRepository.save(sackEntity);
        return DeliveryDto.builder().barcode(barcode).state(sackEntity.getState().getCode()).build();
    }

    @Override
    public FleetTypeEnum getType() {
        return FleetTypeEnum.SACK;
    }
}