package com.hiretrack.service;

import com.hiretrack.dto.EmployeeRegisterDto;
import com.hiretrack.dto.JobseekerRegisterDto;
import com.hiretrack.enums.Role;
import com.hiretrack.model.Employer;
import com.hiretrack.model.JobSeeker;
import com.hiretrack.model.User;
import com.hiretrack.repository.JobRepository;
import com.hiretrack.repository.JobSeekerRepository;
import com.hiretrack.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobSeekerService {
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public JobSeeker getByUsername(String name) {
        return jobSeekerRepository.findByUserUsername(name);
    }

    public void addUser(@Valid JobseekerRegisterDto dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user  = new User();
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setRole(Role.SEEKER);

        // save the user
        userRepository.save(user);

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setName(dto.name());
        jobSeeker.setResumeSummary(dto.summary());
        jobSeeker.setUser(user);

        // save the employer
        jobSeekerRepository.save(jobSeeker);
    }
}
