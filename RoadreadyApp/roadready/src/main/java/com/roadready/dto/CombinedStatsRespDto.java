package com.roadready.dto;

import java.util.List;

public record CombinedStatsRespDto(
        List<String> label,
        List<Integer> data
) {
}
