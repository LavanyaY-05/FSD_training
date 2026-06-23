package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgentUpdateReqDto(
        @NotBlank(message = "This field is mandatory")
@NotNull(message = "This field is mandatory")
String firstname,

        String lastname,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String email,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String phoneNumber
) {
}
