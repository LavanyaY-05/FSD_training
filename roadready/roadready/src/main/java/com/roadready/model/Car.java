package com.roadready.model;

import com.roadready.enums.CarStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String type;

    private String fuelType;
    private int seats;

    @Column(nullable = false)
    private double PricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
