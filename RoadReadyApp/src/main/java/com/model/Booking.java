package com.model;

import com.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String pickupDateTime;

    @Column(nullable = false)
    private String dropOffDateTime;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private String pickUpLocation;

    @Column(nullable = false)
    private String dropOffLocation;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Car car;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", pickupDateTime=" + pickupDateTime +
                ", dropOffDateTime=" + dropOffDateTime +
                ", totalAmount=" + totalAmount +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                ", car=" + car +
                '}';
    }
}
