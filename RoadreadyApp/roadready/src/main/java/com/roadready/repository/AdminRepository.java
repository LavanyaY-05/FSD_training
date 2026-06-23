package com.roadready.repository;

import com.roadready.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserUsername(String username);
}
