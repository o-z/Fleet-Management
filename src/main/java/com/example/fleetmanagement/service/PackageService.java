package com.example.fleetmanagement.service;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import com.example.fleetmanagement.kafka.producer.FleetLogProducerService;
import com.example.fleetmanagement.model.converter.PackageConverter;
import com.example.fleetmanagement.model.dto.DeliveryDto;
import com.example.fleetmanagement.model.dto.LogDto;
import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import com.example.fleetmanagement.model.entity.PackageEntity;
import com.example.fleetmanagement.model.entity.SackEntity;
import com.example.fleetmanagement.model.enums.FleetStateEnum;
import com.example.fleetmanagement.model.enums.FleetTypeEnum;
import com.example.fleetmanagement.model.request.PackageCreateRequest;
import com.example.fleetmanagement.model.request.PackagePutToSackRequest;
import com.example.fleetmanagement.model.response.PackageResponse;
import com.example.fleetmanagement.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageService implements FleetFactory {
    private final PackageRepository packageRepository;
    private final SackService sackService;
    private final PackageConverter packageConverter;
    private final DeliveryPointService deliveryPointService;
    private final FleetLogProducerService fleetLogProducerService;

    @Transactional
    public PackageResponse create(final PackageCreateRequest request) {
        DeliveryPointEntity deliveryPointEntity = deliveryPointService.getById(request.getDeliveryPointId());
        PackageEntity savedPackageEntity = packageRepository.save(PackageEntity.builder()
                .desi(request.getDesi())
                .state(FleetStateEnum.CREATED)
                .deliveryPointEntity(deliveryPointEntity)
                .build());
        savedPackageEntity.setBarcode(FleetTypeEnum.PACKAGE.getPrefix() + savedPackageEntity.getId());
        packageRepository.save(savedPackageEntity);
        return packageConverter.convertFrom(savedPackageEntity);
    }

    @Transactional(readOnly = true)
    public PackageResponse getByBarcode(final String barcode) {
        PackageEntity packageEntity = packageRepository.findByBarcode(barcode)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.PACKAGE_NOT_FOUND_ERROR));
        return packageConverter.convertFrom(packageEntity);
    }

    @Transactional
    public void putToSack(final PackagePutToSackRequest request) {
        SackEntity sackEntityByBarcode = sackService.getSackEntityByBarcode(request.getSackBarcode());
        Set<PackageEntity> packageEntities = packageRepository.findAllByBarcodeIn(request.getPackageBarcodes());
        if (packageEntities.stream().anyMatch(packageEntity ->
                !Objects.equals(packageEntity.getDeliveryPointEntity().getId(),
                        sackEntityByBarcode.getDeliveryPointEntity().getId()))) {
            throw new FleetManagementException(ErrorCodeEnum.DELIVERY_POINT_NOT_MATCH_ERROR);
        }
        packageEntities.forEach(packageEntity -> {
            packageEntity.setSackEntity(sackEntityByBarcode);
            packageEntity.setState(FleetStateEnum.LOADED_INTO_SACK);
        });
        packageRepository.saveAll(packageEntities);
    }

    @Transactional
    @Override
    public DeliveryDto stateChangeByConditional(final String barcode, final Integer desiredDeliveryPoint) {
        PackageEntity packageEntity = packageRepository.findByBarcode(barcode)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.PACKAGE_NOT_FOUND_ERROR));
        if (desiredDeliveryPoint.equals(packageEntity.getDeliveryPointEntity().getId())) {
            packageEntity.setState(FleetStateEnum.UNLOADED);
            if (packageEntity.getSackEntity() != null && packageEntity.getSackEntity().getPackageEntities().stream()
                    .allMatch(packageInSack -> packageInSack.getState().equals(FleetStateEnum.UNLOADED))) {
                packageEntity.getSackEntity().setState(FleetStateEnum.UNLOADED);
            }
        } else {
            packageEntity.setState(FleetStateEnum.LOADED);
            fleetLogProducerService.sendMessage(LogDto.builder()
                    .barcode(barcode)
                    .desiredDeliveryPoint(desiredDeliveryPoint)
                    .realDeliveryPoint(packageEntity.getDeliveryPointEntity().getId())
                    .build());
        }
        packageRepository.save(packageEntity);
        return DeliveryDto.builder().barcode(barcode).state(packageEntity.getState().getCode()).build();

    }


    @Override
    public FleetTypeEnum getType() {
        return FleetTypeEnum.PACKAGE;
    }
}
