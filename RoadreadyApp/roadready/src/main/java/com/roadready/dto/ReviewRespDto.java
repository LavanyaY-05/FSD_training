package com.roadready.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record ReviewRespDto(

        String customerName,
        int rating,
        String comment,
        Instant createdAt
) {
}
