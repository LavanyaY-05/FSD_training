package com.roadready.dto;

import com.roadready.enums.DeliveryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AddBookingDto(
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        LocalDateTime pickupDateTime,

        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        LocalDateTime dropdownDateTime,

        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String pickupLocation,

        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String dropdownLocation,

        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String deliveryType,

        String couponCode
) {
}
