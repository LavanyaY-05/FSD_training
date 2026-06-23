package com.roadready.controller;

import com.roadready.dto.BookingAgentPaginaation;
import com.roadready.dto.InspectionReqDto;
import com.roadready.service.BookingService;
import com.roadready.service.VehicleInspectionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/inspection")
@CrossOrigin(origins = "http://localhost:5173/")

@AllArgsConstructor
public class VehicleInspectionController {
    private final VehicleInspectionService vehicleInspectionService;
    private final BookingService bookingService;

    @PostMapping("/add/{id}")
    public void add(@PathVariable int id, @Valid @RequestBody InspectionReqDto dto, Principal principal) {
        vehicleInspectionService.add(id, dto, principal.getName());
    }

    @GetMapping("/pre-rent")
    public BookingAgentPaginaation getPreRent(@RequestParam(required = false, defaultValue = "0") int pages,
                                              @RequestParam(required = false, defaultValue = "5") int size,
                                              Principal principal) {
        return bookingService.getPreRent(pages, size, principal.getName());
    }

    @GetMapping("/post-rent")
    public BookingAgentPaginaation getPostRent(@RequestParam(required = false, defaultValue = "0") int pages,
                                               @RequestParam(required = false, defaultValue = "5") int size,
                                               Principal principal) {
        return bookingService.getPostRent(pages, size, principal.getName());
    }
}
