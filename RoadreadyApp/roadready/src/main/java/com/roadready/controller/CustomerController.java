package com.roadready.controller;

import com.roadready.dto.*;
import com.roadready.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173/")

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signUp")
    public void addCustomer(@Valid @RequestBody CustomerDetailsReqDto dto) {
        customerService.addCustomer(dto);
    }

    @GetMapping("/customerprofile")
    public CustomerDetailsRespDto userprofile(Principal principal) {
        String username = principal.getName();
        return customerService.getByUsername(username);
    }

    @PutMapping("/update")
    public void updateCustomer(Principal principal, @Valid @RequestBody UpdateReqDto dto) {
        customerService.updateCustomer(principal.getName(), dto);
    }

    // get all customer
    @GetMapping("/getAll")
    public List<CustomerDetailsRespDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    //get By id
    @GetMapping("/get/{id}")  //a
    public CustomerDetailsRespDto getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

}
