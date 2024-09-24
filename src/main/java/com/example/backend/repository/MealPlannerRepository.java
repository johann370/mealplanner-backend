package com.example.backend.repository;

import com.example.backend.model.MealPlanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealPlannerRepository extends JpaRepository<MealPlanner, Long> {
    List<MealPlanner> getMealPlannersByUserId(String userId);
}
