package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PasswordDto(
        @NotBlank(message = "should not be blank")
        @NotNull(message = "This is mandatory")
        String newPassword
) {
}
