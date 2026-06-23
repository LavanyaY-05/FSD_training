package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerDetailsReqDto(

        @NotNull(message = "This field is required")
        @NotBlank(message = "This field is required")
        String firstName,

        String lastName,

        @NotNull(message = "This field is required")
        @NotBlank(message = "This field is required")
        String email,

        @NotNull(message = "This field is required")
        @NotBlank(message = "This field is required")
        String phoneNumber,

        @NotNull(message = "This field is required")
        @NotBlank(message = "This field is required")
        @Size(min = 4, message = "Username at-least have 4 characters")
        String username,

        @NotNull(message = "This field is required")
        @NotBlank(message = "This field is required")
        @Size(min = 4, message = "Password should be more than 4")
        String password
) {
}
