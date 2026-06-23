package com.roadready.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponRespDto(
        int id,
        String couponCode,
        BigDecimal discountValue,
        LocalDate expiryDate,
        boolean isActive,
        int maxUsage,
        int usedCount
) {
}
