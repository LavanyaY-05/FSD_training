package com.hiretrack.service;


import com.hiretrack.Mapper.JobMapper;
import com.hiretrack.dto.JobPagination;
import com.hiretrack.dto.JobReqDto;
import com.hiretrack.dto.JobRespDto;
import com.hiretrack.exceptions.ResourceNotFoundException;
import com.hiretrack.model.Employer;
import com.hiretrack.model.Job;
import com.hiretrack.repository.JobRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    private final JobMapper jobMapper;
    private final EmployerService employerService;
    private final JobRepository jobRepository;

    public void add(String username, @Valid JobReqDto dto) {
        Employer employer = employerService.getUserByUsername(username);

        Job job = jobMapper.mapDtoToEntity(dto);
        job.setEmployer(employer);

        jobRepository.save(job);

    }

    public JobPagination getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<Job> pages = jobRepository.findAll(pageable);

        List<JobRespDto> jobs = pages.stream()
                .map(jobMapper::mapEntityToDto)
                .toList();

        return jobMapper.mapPagination(pages,jobs);
    }

    public Job getById(int id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Job ID"));
    }
}
