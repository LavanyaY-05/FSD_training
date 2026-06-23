package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InspectionReqDto(
        @NotNull(message = "Type is mandatory")
        @NotBlank(message = "Type is mandatory")
        String inspectionType,

        double fuelLevel,

        double odometer,

        @NotNull(message = "Type is mandatory")
        @NotBlank(message = "Type is mandatory")
        String condition,

        boolean hasDamage,
        String damageReport
) {
}
