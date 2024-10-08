package com.example.backend.repository;

import com.example.backend.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findMealsByRecipesId(Long recipeId);
    List<Meal> findMealsByMealDayAndMealType(String mealDay, String mealType);
    List<Meal> findMealsByPlannerId(Long mealPlannerId);
}
