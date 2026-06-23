package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponReqDto(
        @NotNull( message = "This is a Mandatory Field")
        @NotBlank( message = "This is Field should not be blank")
        String couponCode,
        @NotNull( message = "This is a Mandatory Field")
        @NotBlank( message = "This is Field should not be blank")
        BigDecimal discountValue,
        @NotNull( message = "This is a Mandatory Field")
        @NotBlank( message = "This is Field should not be blank")
        LocalDate expiryDate,
        @NotNull( message = "This is a Mandatory Field")
        int maxUsage
) {
}
