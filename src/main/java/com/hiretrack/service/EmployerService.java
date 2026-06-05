package com.hiretrack.service;

import com.hiretrack.dto.EmployeeRegisterDto;
import com.hiretrack.enums.Role;
import com.hiretrack.model.Employer;
import com.hiretrack.model.User;
import com.hiretrack.repository.EmployerRepository;
import com.hiretrack.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public Employer getUserByUsername(String username) {
        return employerRepository.findByUserUsername(username);
    }

    public void addUser(@Valid EmployeeRegisterDto dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user  = new User();
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setRole(Role.EMPLOYER);

        // save the user
        userRepository.save(user);

        Employer employer = new Employer();
        employer.setCompanyName(dto.companyName());
        employer.setUser(user);

        // save the employer
        employerRepository.save(employer);


    }
}
