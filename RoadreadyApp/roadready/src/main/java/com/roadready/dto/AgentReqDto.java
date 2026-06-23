package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AgentReqDto(

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String firstname,

        String lastname,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String email,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String phoneNumber,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String location,

        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        @Size(min = 4, message = "size should be at-least 4 characters")
        String username

) {
}
