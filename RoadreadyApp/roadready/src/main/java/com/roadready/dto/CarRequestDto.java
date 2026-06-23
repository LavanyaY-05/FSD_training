package com.roadready.dto;

import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.FuelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRequestDto (


        @NotNull(message = "this field is mandatory")
        @NotBlank(message = "this field is mandatory")
        String brand,
        @NotNull(message = "this field is mandatory")
        @NotBlank(message = "this field is mandatory")
        String model,
        @NotNull(message = "this field is mandatory")
        CarType type,
        @NotNull(message = "this field is mandatory")
        int modelYear,
        @NotNull(message = "this field is mandatory")
        CarTransmission carTransmission,
        @NotNull(message = "this field is mandatory")
        FuelType fuelType,
        @NotNull(message = "this field is mandatory")
        int seats,
        double mileage,
      BigDecimal PricePerHour,
        @NotNull(message = "this field is mandatory")
        @NotBlank(message = "this field is mandatory")

        String location,

        String address,

        String image
){
}
