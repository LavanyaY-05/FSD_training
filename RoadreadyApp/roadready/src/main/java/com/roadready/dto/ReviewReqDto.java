package com.roadready.dto;

import jakarta.validation.constraints.*;

public record ReviewReqDto(
        @NotNull(message = "Rating is required")
@Min(value = 1, message = "Rating must be at least 1")
@Max(value = 5, message = "Rating must not exceed 5")
int rating,

@NotBlank(message = "Comment is required")
@Size(max = 500, message = "Comment must not exceed 500 characters")
String comment

) {
}
