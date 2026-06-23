package com.roadready.controller;

import com.roadready.dto.AgentDetailsRespDto;
import com.roadready.dto.AgentReqDto;
import com.roadready.dto.AgentUpdateReqDto;
import com.roadready.service.AgentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/agent")
@CrossOrigin(origins = "http://localhost:5173/")
public class AgentController {

    private final AgentService agentService;

    // add agent
    @PostMapping("/add")
    public void addAgent(@Valid @RequestBody AgentReqDto dto) {
        agentService.addAgent(dto);
    }

    @GetMapping("/agentprofile")
    public AgentDetailsRespDto userprofile(Principal principal) {
        String username = principal.getName();
        return agentService.getByUsername(username);
    }

    @PutMapping("/update")
    public void updateAgent(Principal principal, @Valid @RequestBody AgentUpdateReqDto dto) {
        agentService.updateAgent(principal.getName(), dto);
    }

    @GetMapping("/get/{id}")
    public AgentDetailsRespDto getAgent(@PathVariable int id) {
        return agentService.getAgent(id);
    }


    @GetMapping("/getAll")
    public List<AgentDetailsRespDto> getAllAgent() {
        return agentService.getAllAgent();
    }


    @GetMapping("/get-by-location")
    public List<AgentDetailsRespDto> getAllByLocation(@RequestParam String location) {
        return agentService.getAllByLocation(location);
    }


}
