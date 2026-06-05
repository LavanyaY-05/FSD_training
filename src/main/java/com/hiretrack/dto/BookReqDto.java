package com.hiretrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.engine.internal.ImmutableEntityEntry;

public record BookReqDto(
        @NotNull(message = "This is a  required field")
        @NotBlank(message = "This is a  required field")
        String title,

        @NotNull(message = "This is a  required field")
        @NotBlank(message = "This is a  required field")
        String summary
) {
}
