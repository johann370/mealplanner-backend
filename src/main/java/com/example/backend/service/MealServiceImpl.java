package com.example.backend.service;

import com.example.backend.exception.ObjectNotFoundException;
import com.example.backend.model.Meal;
import com.example.backend.model.Recipe;
import com.example.backend.repository.MealRepository;
import com.example.backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Meal addRecipeToMeal(Long mealId, Long recipeId) {
        Recipe newRecipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ObjectNotFoundException("Could not find recipe with id: " + recipeId));

        return mealRepository.findById(mealId)
                .map(meal -> {
                    meal.addRecipe(newRecipe);
                    return mealRepository.save(meal);
                }).orElseThrow(() -> new ObjectNotFoundException("Could not find meal with id: " + mealId));
    }

    @Override
    public void deleteRecipeFromMeal(Long mealId, Long recipeId) {
        mealRepository.findById(mealId)
                .map(meal -> {
                    meal.getRecipes().removeIf(recipe -> recipe.getId().equals(recipeId));
                    return mealRepository.save(meal);
                }).orElseThrow(() -> new ObjectNotFoundException("Could not find meal with id: " + mealId));
    }
}
