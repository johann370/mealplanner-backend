package com.example.backend.service;

import com.example.backend.model.Meal;

public interface MealService {
    Meal addRecipeToMeal(Long mealId, Long recipeId);

    void deleteRecipeFromMeal(Long mealId, Long recipeId);
}
