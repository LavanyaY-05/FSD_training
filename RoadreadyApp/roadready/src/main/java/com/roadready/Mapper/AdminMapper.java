package com.roadready.Mapper;

import com.roadready.dto.AdminDetailsRespDto;
import com.roadready.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDetailsRespDto mapAdminDetailsToDto(Admin admin){

        return new AdminDetailsRespDto(
                admin.getFirstname(),
                admin.getLastname(),
                admin.getEmail(),
                admin.getPhoneNumber(),
                admin.getJobTitle(),
                admin.getUser().getUsername()
        );

    }
}
