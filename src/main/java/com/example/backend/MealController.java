package com.example.backend;

import com.example.backend.service.MySecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000"})
public class MealController {

    @Autowired
    MySecurityService mySecurityService;

    private final MealRepository repository;
    private final RecipeRepository recipeRepository;

    MealController(MealRepository repository, RecipeRepository recipeRepository){
        this.repository = repository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/meals")
    List<Meal> getMeals() {
        return repository.findAll();
    }

    @GetMapping("/meals/calendar")
    Calendar getMealsMonday() {
        return new Calendar(repository);
    }

    @PostMapping("/meals")
    Meal addMeal(@RequestBody Meal newMeal){
        return repository.save(newMeal);
    }

    @DeleteMapping("/meals/{id}")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #id)")
    void deleteMeal(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/meals/{id}/recipe")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #id)")
    Meal addRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id) {
        return repository.findById(id)
                .map(meal -> {
                    meal.addRecipe(newRecipe);
                    return repository.save(meal);
                }).orElseThrow();
    }

    @PostMapping("/meals/{id}/recipe/{recipeId}")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #id)")
    Meal addRecipe(@PathVariable Long id, @PathVariable Long recipeId) {
        Recipe newRecipe = recipeRepository.findById(recipeId).orElseThrow();
        return repository.findById(id)
                .map(meal -> {
                    meal.addRecipe(newRecipe);
                    return repository.save(meal);
                }).orElseThrow();
    }

    @DeleteMapping("/meals/{id}/recipe/{recipeId}")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #id)")
    void deleteRecipe(@PathVariable Long id, @PathVariable Long recipeId){
        repository.findById(id)
                .map(meal -> {
                    meal.getRecipes().removeIf(recipe -> recipe.getId().equals(recipeId));
                    return repository.save(meal);
        });
    }
}
