package com.roadready.dto;

import java.util.List;

public record ReviewCarPaginationDto(

        List<ReviewRespDto> reviews
) {
}
