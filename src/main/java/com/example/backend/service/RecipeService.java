package com.example.backend.service;

import com.example.backend.model.Recipe;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> getRecipes();
    Optional<Recipe> getRecipe(Long recipeId);
    Recipe addRecipe(Recipe newRecipe);
    void deleteRecipe(Long recipeId);
    void updateFromSheets() throws GeneralSecurityException, IOException;
}
