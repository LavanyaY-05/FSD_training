package com.roadready.dto;

import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.DeliveryType;
import com.roadready.enums.FuelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CarSearchReqDto(

        @NotBlank(message = "Select a Location to continue")
        @NotNull(message = "Select a Location to continue")
        String location,

        LocalDateTime pickUpDate,

        LocalDateTime dropDownDate,

        String fuelType,
        String transmission,
        String type


) {
}
