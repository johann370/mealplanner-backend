package com.example.backend.service;

import com.example.backend.model.Meal;

public interface MealService {
    Meal addRecipe(Long mealId, Long recipeId);

    void deleteRecipe(Long mealId, Long recipeId);
}
