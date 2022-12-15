package com.example.fleetmanagement.model.entity;

import com.example.fleetmanagement.model.enums.FleetStateEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SACK", schema = "FLEET_MANAGEMENT",
        indexes = @Index(name = "PACKAGE_BARCODE_INDEX", columnList = "BARCODE"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"BARCODE"}))
@EntityListeners(AuditingEntityListener.class)

public class SackEntity {

    @Id
    @Column(name = "SACK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BARCODE")
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private FleetStateEnum state;

    @OneToMany(mappedBy = "sackEntity", cascade = CascadeType.ALL)
    private Set<PackageEntity> packageEntities = new HashSet<>();

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
