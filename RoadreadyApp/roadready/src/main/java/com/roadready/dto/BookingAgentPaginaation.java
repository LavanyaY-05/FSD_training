package com.roadready.dto;

import java.util.List;

public record BookingAgentPaginaation(
        long totalRecords,
        int totalPages,
        List<BookingAgentDto> bookingDetails
) {
}
