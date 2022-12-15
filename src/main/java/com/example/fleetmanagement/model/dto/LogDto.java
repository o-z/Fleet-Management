package com.example.fleetmanagement.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogDto implements Serializable {
    private Integer desiredDeliveryPoint;
    private Integer realDeliveryPoint;
    private String barcode;

}
