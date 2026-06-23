package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReqDto(

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String firstName,

        String lastName,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String email,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String phoneNumber
) {
}
