package com.hiretrack.controller;

import com.hiretrack.dto.EmployeeRegisterDto;
import com.hiretrack.service.EmployerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employer")
@AllArgsConstructor
public class EmployerController {

    private final EmployerService employerService;
    @PostMapping("/register")
    public void register(@Valid @RequestBody EmployeeRegisterDto dto){
        employerService.addUser(dto);
    }

}
