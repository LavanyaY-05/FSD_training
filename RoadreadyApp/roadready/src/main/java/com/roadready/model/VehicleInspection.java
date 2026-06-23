package com.roadready.model;

import com.roadready.enums.InspectionType;
import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
public class VehicleInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private InspectionType inspectionType;

    @Column(length = 1000)
    private String carCondition;

    @Column(nullable = false)
    private double fuelLevel;

    @Column(nullable = false)
    private double odometer;

    private String damageReport;
    private boolean hasDamage;

    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Booking booking;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
