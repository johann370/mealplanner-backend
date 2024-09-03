package com.example.backend.service;

import com.example.backend.dto.CalendarDTO;
import com.example.backend.model.MealPlanner;

import java.util.List;

public interface MealPlannerService {
    public MealPlanner getMealPlanner(Long mealPlannerId);

    public void deleteMealPlanner(Long mealPlannerId);

    public MealPlanner createMealPlanner(String userId, String plannerName);

    public List<MealPlanner> getUserMealPlanners(String userId);
}
