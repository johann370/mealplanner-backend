package com.example.backend.controller;

import com.example.backend.model.Meal;
import com.example.backend.service.MealService;
import com.example.backend.service.MySecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
public class MealController {

    private final MySecurityService mySecurityService;

    private final MealService mealService;

    @Autowired
    public MealController(MySecurityService mySecurityService, MealService mealService){
        this.mySecurityService = mySecurityService;
        this.mealService = mealService;
    }

    @PostMapping("/meals/{mealId}/recipe/{recipeId}")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #mealId)")
    Meal addRecipe(@PathVariable Long mealId, @PathVariable Long recipeId) {
        return mealService.addRecipe(mealId, recipeId);
    }

    @DeleteMapping("/meals/{mealId}/recipe/{recipeId}")
    @PreAuthorize("@mySecurityService.ownsMeal(principal, #mealId)")
    void deleteRecipe(@PathVariable Long mealId, @PathVariable Long recipeId){
        mealService.deleteRecipe(mealId, recipeId);
    }
}
