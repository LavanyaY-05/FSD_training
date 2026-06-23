package com.roadready.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdminDetailsReqDto(

        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String firstName,
        String lastName,
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String email,
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String phoneNumber,
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String jobTitle,
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        @Size(min = 4)
        String username,
        @NotNull(message = "This is a Mandatory Field")
        @NotBlank(message = "This is a Mandatory Field")
        String password

) {
}
