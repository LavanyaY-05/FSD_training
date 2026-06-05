package com.hiretrack.Mapper;

import com.hiretrack.dto.ApplicationPaginationDto;
import com.hiretrack.dto.ApplicationResponseDto;
import com.hiretrack.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {

    public ApplicationResponseDto mapEntityToDto(Application application){
        return new ApplicationResponseDto(
         application.getId(),
         application.getAppliedAt(),
         application.getJob().getTitle(),
         application.getJob().getEmployer().getCompanyName()
        );
    }

    public ApplicationPaginationDto mapPagination(Page<Application> applicationPage, List<ApplicationResponseDto> dto){
        return new ApplicationPaginationDto(
                applicationPage.getTotalElements(),
                applicationPage.getTotalPages(),
                dto
        );
    }
}
