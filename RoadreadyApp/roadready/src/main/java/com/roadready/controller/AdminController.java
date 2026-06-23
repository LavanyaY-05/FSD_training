package com.roadready.controller;

import com.roadready.dto.*;
import com.roadready.service.AdminService;
import com.roadready.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173/")

public class AdminController {

    private final AdminService adminService;
    private final BookingService bookingService;


    @PutMapping("/update")
    public void updateAdmin(Principal principal, @Valid @RequestBody UpdateReqDto dto) {
        adminService.updateAdmin(principal.getName(), dto);
    }

    @PostMapping("/register")
    public void addAdmin(@Valid @RequestBody AdminDetailsReqDto dto) {
        adminService.addAdmin(dto);
    }

    @GetMapping("/adminprofile")
    public AdminDetailsRespDto userprofile(Principal principal) {
        String username = principal.getName();
        return adminService.getByUsername(username);
    }

    @GetMapping("/combinedStats")
    public CombinedStatsRespDto combinedStats() {
        return adminService.combinedStats();
    }


    @GetMapping("/getCarStats")
    public StatsDto carStats() {
        return adminService.carStats();
    }


    @GetMapping("/getAgentStats")
    public StatsDto agentStats() {
        return adminService.agentStats();
    }

    @GetMapping("/getUnassignedAgent")
    public BookingPaginationResponse findUnAssignedAgent(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return bookingService.getAll(page, size);
    }

    @GetMapping("/getAgents/{bookingId}")
    public List<AgentDetailsRespDto> findUnAssignedAgent(@PathVariable int bookingId
    ) {
        return bookingService.getAgents(bookingId);
    }

    @PostMapping("/assign/{bookingId}/{agentId}")
    public void assignAgentToBooking(@PathVariable int bookingId, @PathVariable int agentId) {
        bookingService.assignAgent(bookingId, agentId);
    }


}
