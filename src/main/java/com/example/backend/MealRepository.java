package com.example.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findMealsByRecipesId(Long recipeId);
}
