package com.example.fleetmanagement.service;

import com.example.fleetmanagement.exception.ErrorCodeEnum;
import com.example.fleetmanagement.exception.FleetManagementException;
import com.example.fleetmanagement.model.converter.DeliveryPointConverter;
import com.example.fleetmanagement.model.entity.DeliveryPointEntity;
import com.example.fleetmanagement.model.request.DeliveryPointCreateRequest;
import com.example.fleetmanagement.model.response.DeliveryPointCreateResponse;
import com.example.fleetmanagement.repository.DeliveryPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPointService {
    private final DeliveryPointRepository deliveryPointRepository;
    private final DeliveryPointConverter deliveryPointConverter;

    @Transactional
    public DeliveryPointCreateResponse create(final DeliveryPointCreateRequest request) {
        DeliveryPointEntity savedDeliveryPointEntity = deliveryPointRepository.save(DeliveryPointEntity.builder()
                .type(request.getType())
                .name(request.getName())
                .location(request.getLocation())
                .build());
        return deliveryPointConverter.convertFrom(savedDeliveryPointEntity);

    }

    @Transactional(readOnly = true)
    public DeliveryPointEntity getById(final Integer id) {
        return deliveryPointRepository.findById(id)
                .orElseThrow(() -> new FleetManagementException(ErrorCodeEnum.DELIVERY_POINT_NOT_FOUND_ERROR));
    }
}