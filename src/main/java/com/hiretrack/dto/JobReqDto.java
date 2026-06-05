package com.hiretrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobReqDto(

        @NotBlank(message = "This is a required Field")
        String title,
        @NotBlank(message = "This is a required Field")
        String description,
        String location,
        @NotNull(message = "This is a required Field")
        double salary
) {
}
