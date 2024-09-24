package com.example.backend.controller;

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
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/recipes/{recipeId}")
    Optional<Recipe> getRecipe(@PathVariable Long recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    Recipe addRecipe(@Valid @RequestBody Recipe newRecipe){
        return recipeService.addRecipe(newRecipe);
    }

    @DeleteMapping("/recipes/{recipeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    @PutMapping ("/recipes/sheets")
    void updateFromSheets() throws GeneralSecurityException, IOException {
        recipeService.updateFromSheets();
    }
}
