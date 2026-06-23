package com.roadready.dto;

import java.util.List;

public record BookingPaginationResponse(
        long totalRecords,
        int totalPages,
        List<BookingDetailsDto> bookingDetails
) {
}
