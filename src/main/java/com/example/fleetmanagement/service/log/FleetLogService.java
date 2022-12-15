package com.example.fleetmanagement.service.log;

import com.example.fleetmanagement.model.document.FleetLogDocument;
import com.example.fleetmanagement.model.dto.LogDto;
import com.example.fleetmanagement.repository.log.FleetLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FleetLogService {
    private final FleetLogRepository fleetLogRepository;

    @Transactional
    public void saveLog(final LogDto logDto) {
        fleetLogRepository.save(FleetLogDocument.builder()
                .barcode(logDto.getBarcode())
                .desiredDeliveryPoint(logDto.getDesiredDeliveryPoint())
                .realDeliveryPoint(logDto.getRealDeliveryPoint())
                .build());
    }

}