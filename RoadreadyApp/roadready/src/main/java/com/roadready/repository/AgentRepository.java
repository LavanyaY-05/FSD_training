package com.roadready.repository;

import com.roadready.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findByLocation(String location);

    Agent findByUserUsername(String username);
}
