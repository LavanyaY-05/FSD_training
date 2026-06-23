package com.roadready.dto;

public record AdminDetailsRespDto(
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        String jobTitle,
        String username
) {
}
