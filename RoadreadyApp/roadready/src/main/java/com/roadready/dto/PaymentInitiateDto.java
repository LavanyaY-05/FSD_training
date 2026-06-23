package com.roadready.dto;

import com.roadready.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentInitiateDto(
        @NotNull(message = "Payment Method is mandatory")
        @NotBlank(message = "Payment Method should not be blank")
        PaymentMethod paymentMethod
) {
}
