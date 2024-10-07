package com.example.backend.service;

import com.example.backend.model.Recipe;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> getRecipes();
    Optional<Recipe> getRecipeById(Long recipeId);
    Recipe createRecipe(Recipe newRecipe);
    void deleteRecipe(Long recipeId);
    void updateRecipesFromSheets() throws GeneralSecurityException, IOException;
}
