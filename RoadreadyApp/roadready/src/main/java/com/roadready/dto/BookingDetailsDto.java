package com.roadready.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingDetailsDto(

        int bookingId,
        int customerId,
        int carId,
        String brand,
        String model,
        LocalDateTime pickupDateTime,
        LocalDateTime dropdownDateTime,
        BigDecimal totalAmount,
        String status,
        String kyc_status
) {
}
