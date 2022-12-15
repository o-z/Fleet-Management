package com.example.fleetmanagement.model.entity;

import com.example.fleetmanagement.model.enums.VehicleStatusEnum;
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
@Table(name = "VEHICLE", schema = "FLEET_MANAGEMENT",
        indexes = @Index(name = "PACKAGE_PLATE_INDEX", columnList = "PLATE"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"PLATE"}))
@EntityListeners(AuditingEntityListener.class)
public class VehicleEntity {

    @Id
    @Column(name = "VEHICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PLATE")
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private VehicleStatusEnum status;

    @OneToMany(mappedBy = "vehicleEntity", cascade = CascadeType.ALL)
    private Set<PackageEntity> packageEntities = new HashSet<>();

    @OneToMany(mappedBy = "vehicleEntity", cascade = CascadeType.ALL)
    private Set<SackEntity> sackEntities = new HashSet<>();

    @CreatedDate
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
