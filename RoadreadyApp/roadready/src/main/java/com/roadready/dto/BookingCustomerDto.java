package com.roadready.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingCustomerDto(
        String username,
        int id,
        String carBrand,
        String carModel,
        String carType,
        int modelYear,
        LocalDateTime pickUpDateTime,
        LocalDateTime dropDownDateTime,
        String pickUpLocation,
        String dropDownLocation,
        String deliveryType,
        BigDecimal totalPrice,
        String bookingStatus,
        String kycStatus
) {
}
