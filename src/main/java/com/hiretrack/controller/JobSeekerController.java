package com.hiretrack.model;

import com.hiretrack.dto.EmployeeRegisterDto;
import com.hiretrack.dto.JobseekerRegisterDto;
import com.hiretrack.service.JobSeekerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jobseeker")
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;
    @PostMapping("/register")
    public void register(@Valid @RequestBody JobseekerRegisterDto dto){
        jobSeekerService.addUser(dto);
    }
}
