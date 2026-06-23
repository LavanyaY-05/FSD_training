package com.roadready.repository;

import com.roadready.model.VehicleInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Integer> {
}
