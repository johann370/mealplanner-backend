package com.example.backend.controller;

import com.example.backend.controller.swagger.RecipeControllerSwagger;
import com.example.backend.model.Recipe;
import com.example.backend.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
public class RecipeController implements RecipeControllerSwagger {
    private final RecipeService recipeService;

    @Autowired
    RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @Override
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @Override
    @GetMapping("/recipes/{recipeId}")
    public Optional<Recipe> getRecipeById(@PathVariable Long recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    @Override
    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe createRecipe(@Valid @RequestBody Recipe newRecipe){
        return recipeService.createRecipe(newRecipe);
    }

    @Override
    @DeleteMapping("/recipes/{recipeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    @Override
    @PutMapping ("/recipes/sheets")
    public void updateRecipesFromSheets() throws GeneralSecurityException, IOException {
        recipeService.updateRecipesFromSheets();
    }
}
