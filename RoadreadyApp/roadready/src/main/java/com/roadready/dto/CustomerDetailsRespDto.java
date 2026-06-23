package com.roadready.dto;



public record CustomerDetailsRespDto (
        String firstname,
        String lastname,
        String email,
        String phoneNumber,
        String username
){
}
