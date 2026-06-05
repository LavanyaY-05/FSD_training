package com.hiretrack.Mapper;

import com.hiretrack.dto.JobPagination;
import com.hiretrack.dto.JobReqDto;
import com.hiretrack.dto.JobRespDto;
import com.hiretrack.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobMapper {


    public Job mapDtoToEntity(JobReqDto dto){
        Job job = new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setLocation(dto.location());
        job.setSalary(dto.salary());
        return job;

    }

    public JobRespDto mapEntityToDto(Job job){
           return new JobRespDto(
                   job.getId(),
                   job.getTitle(),
                   job.getLocation(),
                   job.getSalary(),
                   job.getEmployer().getCompanyName()
           );
    }

    public JobPagination mapPagination(Page<Job> page, List<JobRespDto> list){
        return new JobPagination(
                page.getTotalElements(),
                page.getTotalPages(),
                list
        );
    }
}
