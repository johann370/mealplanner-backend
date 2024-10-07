package com.example.backend.service;

import com.example.backend.model.Planner;

import java.util.List;

public interface PlannerService {
    Planner getPlannerById(Long plannerId);

    void deletePlannerById(Long plannerId);

    Planner createPlanner(String userId, String plannerName);

    List<Planner> getUserPlanners(String userId);
}
