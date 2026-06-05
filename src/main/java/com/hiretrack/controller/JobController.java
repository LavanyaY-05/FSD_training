package com.hiretrack.controller;

import com.hiretrack.dto.JobPagination;
import com.hiretrack.dto.JobReqDto;
import com.hiretrack.dto.JobRespDto;
import com.hiretrack.service.JobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    @PostMapping("/add")
    public void add(@Valid @RequestBody JobReqDto dto,
                     Principal principal){

        String username = principal.getName();
        jobService.add(username, dto);
    }

    @GetMapping("/getAll")
    public JobPagination getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false, defaultValue = "10") int size){
        return jobService.getAll(page, size);
    }



}
