package com.hiretrack.dto;

import java.util.List;

public record ApplicationPaginationDto(
        long totalElements,
        int pages,
        List<ApplicationResponseDto> dto
) {
}
