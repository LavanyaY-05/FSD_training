package com.hiretrack.dto;

import java.util.List;

public record JobPagination(
        long totalElements,
        int pages,
        List<JobRespDto> dto
) {
}
