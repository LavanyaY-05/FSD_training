package com.roadready.model;

import com.roadready.enums.BookingStatus;
import com.roadready.enums.DeliveryType;
import com.roadready.enums.KycStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime pickupDateTime;

    @Column(nullable = false)
    private LocalDateTime dropDownDateTime;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String dropdownLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType deliveryType;

    @Column(nullable = false, precision = 10, scale = 2) // total digit = 10, numbers after decimal point = 2
    private BigDecimal originalAmount;

    @Column(nullable = false,  precision = 10, scale = 2)
    private BigDecimal discountedAmount;

    @Column(nullable = false,  precision = 10, scale = 2)
    private BigDecimal deliveryCharge = new BigDecimal("50.00");

    @Column(nullable = false,  precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;

    private String cancellationInfo;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Coupon coupon;

    @ManyToOne
    private Agent agent;

}
