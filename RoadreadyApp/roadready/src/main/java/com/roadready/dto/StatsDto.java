package com.roadready.dto;

import java.util.List;

public record StatsDto(
        List<String> label,
        List<Long> data
) {
}
