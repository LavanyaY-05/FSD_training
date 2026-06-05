package com.hiretrack.repository;

import com.hiretrack.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    Page<Application> findAllByJobSeekerUserUsername(String name, Pageable pageable);
}
