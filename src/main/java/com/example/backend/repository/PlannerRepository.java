package com.example.backend.repository;

import com.example.backend.model.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    List<Planner> getPlannersByUserId(String userId);
}
