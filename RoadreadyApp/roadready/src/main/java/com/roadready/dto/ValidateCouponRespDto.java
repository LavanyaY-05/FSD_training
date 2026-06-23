package com.roadready.dto;

import java.math.BigDecimal;

public record ValidateCouponRespDto(
        String couponCode,
        String success,
        BigDecimal amount
) {
}
