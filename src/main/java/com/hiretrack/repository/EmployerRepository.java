package com.hiretrack.repository;

import com.hiretrack.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    Employer findByUserUsername(String username);
}
