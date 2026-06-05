package com.hiretrack.service;

import com.hiretrack.Mapper.ApplicationMapper;
import com.hiretrack.dto.ApplicationPaginationDto;
import com.hiretrack.dto.ApplicationResponseDto;
import com.hiretrack.model.Application;
import com.hiretrack.model.Job;
import com.hiretrack.model.JobSeeker;
import com.hiretrack.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final JobService jobService;
    private final JobSeekerService jobSeekerService;
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    public void add(int id, String name) {
       // get the job
        Job job = jobService.getById(id);

        // get the jobseeker
        JobSeeker jobSeeker = jobSeekerService.getByUsername(name);

        // assign th details
        Application application = new Application();
        application.setJobSeeker(jobSeeker);
        application.setJob(job);
        //save the application
        applicationRepository.save(application);

    }

    public ApplicationPaginationDto getAll(int page, int size, String name) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Application> applicationPage = applicationRepository.findAllByJobSeekerUserUsername(name,pageable);

        List<ApplicationResponseDto> applications = applicationPage.stream()
                .map(applicationMapper::mapEntityToDto)
                .toList();

        return applicationMapper.mapPagination(applicationPage,applications);

    }
}
