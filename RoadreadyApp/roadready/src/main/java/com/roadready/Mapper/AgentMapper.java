package com.roadready.Mapper;

import com.roadready.dto.AgentDetailsRespDto;
import com.roadready.model.Agent;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {

    public AgentDetailsRespDto mapAgentEntityToDto(Agent agent) {
        return new AgentDetailsRespDto(
                agent.getId(),
                agent.getFirstname(),
                agent.getLastname(),
                agent.getEmail(),
                agent.getPhoneNumber(),
                agent.getLocation(),
                agent.getUser().getUsername()
        );
    }
}
