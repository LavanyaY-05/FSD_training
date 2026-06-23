package com.roadready.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingByIdRespDto(
        int bookingId,
        int customerId,
        int carId,
        String brand,
        String model,
        LocalDateTime pickupDateTime,
        LocalDateTime dropdownDateTime,
        String pickupLocation,
        String dropdownLocation,
        String deliveryType,
        String couponCode,
        BigDecimal originalAmount,
        BigDecimal DeliveryCharge,
        BigDecimal discountedAmount,
        BigDecimal totalAmount,
        String status,
        String kyc_status
) {
}
