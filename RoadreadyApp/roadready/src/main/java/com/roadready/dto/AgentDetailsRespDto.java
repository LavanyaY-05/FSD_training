package com.roadready.dto;

public record AgentDetailsRespDto(

        int agentId,
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        String location,
        String username
) {
}
