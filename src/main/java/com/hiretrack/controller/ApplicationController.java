package com.hiretrack.controller;

import com.hiretrack.dto.ApplicationPaginationDto;
import com.hiretrack.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/add/{id}")
    public void add(@PathVariable int id, Principal principal){
        applicationService.add(id, principal.getName());
    }


    @GetMapping("/my-applications")
    public ApplicationPaginationDto getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           Principal principal){
        return applicationService.getAll(page, size, principal.getName());
    }
}
