package com.example.backend.service;

import com.example.backend.model.MealPlanner;

import java.util.List;

public interface MealPlannerService {
    MealPlanner getMealPlanner(Long mealPlannerId);

    void deleteMealPlanner(Long mealPlannerId);

    MealPlanner createMealPlanner(String userId, String plannerName);

    List<MealPlanner> getUserMealPlanners(String userId);
}
