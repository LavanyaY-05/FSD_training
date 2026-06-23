package com.roadready.dto;

import java.util.List;

public record CarPaginationDto(
        long totalElements,
        int totalPages,
        List<CarDetailsResponseDto> cars
) {
}
