package com.roadready.dto;

import java.util.List;

public record BookingCustomerPagination(
        long totalRecords,
        int totalPages,
        List<BookingCustomerDto> bookingDetails
) {
}
