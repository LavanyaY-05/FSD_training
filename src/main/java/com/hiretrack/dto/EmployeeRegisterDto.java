package com.hiretrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRegisterDto(
        @NotNull(message = "This is a required field")
        @NotBlank(message = "This is a required field")
        @Size(min = 4)
        String username,
        @NotNull(message = "This is a required field")
        @NotBlank(message = "This is a required field")
        String password,

        @NotNull(message = "This is a required field")
        @NotBlank(message = "This is a required field")
        String companyName

) {
}
