package com.roadready.dto;

import com.roadready.enums.CarStatus;
import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.FuelType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record CarDetailsResponseDto(
        int id,
        String brand,
        String model,
        CarType type,
        int modelYear,
        FuelType fuelType,
        CarTransmission carTransmission,
        int seats,
        BigDecimal pricePerHour,
        String location,
        String address,
        String imageUrl
//        double avgRating

) {
}
