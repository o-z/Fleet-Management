package com.example.fleetmanagement.model.entity;

import com.example.fleetmanagement.model.enums.FleetStateEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PACKAGE", schema = "FLEET_MANAGEMENT",
        indexes = @Index(name = "PACKAGE_BARCODE_INDEX", columnList = "BARCODE"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"BARCODE"}))
@EntityListeners(AuditingEntityListener.class)

public class PackageEntity implements Serializable {

    @Id
    @Column(name = "PACKAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BARCODE")
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private FleetStateEnum state;

    @Column(name = "DESI")
    private Integer desi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SACK_ID")
    private SackEntity sackEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VEHICLE_ID")
    private VehicleEntity vehicleEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELIVERY_POINT_ID", nullable = false)
    private DeliveryPointEntity deliveryPointEntity;


    @CreatedDate
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
