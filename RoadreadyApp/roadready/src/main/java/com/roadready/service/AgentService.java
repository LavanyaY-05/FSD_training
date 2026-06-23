package com.roadready.service;


import com.roadready.Mapper.AgentMapper;
import com.roadready.dto.AgentDetailsRespDto;
import com.roadready.dto.AgentReqDto;
import com.roadready.dto.AgentUpdateReqDto;
import com.roadready.enums.Role;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.exceptions.UserAlreadyPresentException;
import com.roadready.model.Agent;
import com.roadready.model.User;
import com.roadready.repository.AgentRepository;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    @Value("${agent.temp.password}")
    private String password;

    public void addAgent(AgentReqDto dto) {


        // extract the required details
        String username = dto.username();
        String encodedPassword = passwordEncoder.encode(password);

        //save the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(Role.AGENT);

        user = userService.save(user);

        // create obj of the Agent and add the details and save it

        Agent agent = new Agent();
        agent.setFirstname(dto.firstname());
        agent.setLastname(dto.lastname());
        agent.setEmail(dto.email());
        agent.setPhoneNumber(dto.phoneNumber());
        agent.setLocation(dto.location());
        agent.setUser(user);

        agentRepository.save(agent);
    }

    public List<AgentDetailsRespDto> getAllAgent() {

        List<Agent> agents = agentRepository.findAll();

        return agents.stream().map(agentMapper::mapAgentEntityToDto).toList();
    }

    public Agent getById(int id) {
        return agentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Agent Id."));
    }

    public AgentDetailsRespDto getAgent(int id) {
        Agent agent = getById(id);
        return agentMapper.mapAgentEntityToDto(agent);
    }

    public AgentDetailsRespDto getByUsername(String username) {
        return agentMapper.mapAgentEntityToDto(findByUsername(username));

    }


    public Agent findByUsername(String username) {

        return agentRepository.findByUserUsername(username);

    }


    public void updateAgent(String username, @Valid AgentUpdateReqDto dto) {
        Agent agent = findByUsername(username);
        agent.setFirstname(dto.firstname());
        agent.setLastname(dto.lastname());
        agent.setEmail(dto.email());
        agent.setPhoneNumber(dto.phoneNumber());

        agentRepository.save(agent);
    }


    public List<AgentDetailsRespDto> getAllByLocation(String location) {
        List<Agent> agents = agentRepository.findByLocation(location);

        return agents.stream().map(agentMapper::mapAgentEntityToDto).toList();
    }
}
