package com.app.model;

import com.app.enums.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class Car
{
    private int id;
    private String brand;
    private String model;
    private String type;
    private String fuelType;
    private int seats;
    private double PricePerDay;
    private CarStatus status;

    public Car() {
    }

    public Car(int id, String brand, String model, String type, String fuelType, int seats, double pricePerDay, CarStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.fuelType = fuelType;
        this.seats = seats;
        PricePerDay = pricePerDay;
        this.status = status;
    }

    public Car(String brand, String model, String type, String fuelType, int seats, double pricePerDay, CarStatus status) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.fuelType = fuelType;
        this.seats = seats;
        PricePerDay = pricePerDay;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", seats=" + seats +
                ", PricePerDay=" + PricePerDay +
                ", status=" + status +
                '}';
    }
}
