package com.example.fleetmanagement.model.entity;

import com.example.fleetmanagement.model.enums.DeliveryPointEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DELIVERY_POINT", schema = "FLEET_MANAGEMENT",
        indexes = @Index(name = "DELIVERY_POINT_TYPE_INDEX", columnList = "TYPE"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"}))
@EntityListeners(AuditingEntityListener.class)
public class DeliveryPointEntity {

    @Id
    @Column(name = "DELIVERY_POINT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private DeliveryPointEnum type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @CreatedDate
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private Date updateDate;


}
