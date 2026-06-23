package com.roadready.dto;

import java.time.LocalDateTime;

public record BookingAgentDto(
        int bookingId,
        String brand,
        String model,
        String type,
        LocalDateTime pickupDateTime,
        LocalDateTime dropdownDateTime

        ) {
}
