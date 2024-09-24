package com.example.backend.controller;

import com.example.backend.dto.PlannerDTO;
import com.example.backend.model.MealPlanner;
import com.example.backend.service.MealPlannerService;
import com.example.backend.service.MySecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
public class MealPlannerController {

    private final MealPlannerService mealPlannerService;
    private final MySecurityService mySecurityService;

    @Autowired
    MealPlannerController(MealPlannerService mealPlannerService, MySecurityService mySecurityService){
        this.mealPlannerService = mealPlannerService;
        this.mySecurityService = mySecurityService;
    }


    @GetMapping("/planner/{mealPlannerId}")
    @PostAuthorize("@mySecurityService.ownsMealPlanner(principal, #mealPlannerId)")
    MealPlanner getMealPlanner(@PathVariable Long mealPlannerId) {
        return mealPlannerService.getMealPlanner(mealPlannerId);
    }

    @PostMapping("/planner/user/{userId}")
    @PreAuthorize("@mySecurityService.isUser(principal, #userId)")
    MealPlanner createMealPlanner(@PathVariable String userId, @RequestBody PlannerDTO plannerDTO){
        return mealPlannerService.createMealPlanner(userId, plannerDTO.plannerName());
    }

    @DeleteMapping("/planner/{mealPlannerId}")
    @PreAuthorize("@mySecurityService.ownsMealPlanner(principal, #mealPlannerId)")
    void deleteMealPlanner(@PathVariable Long mealPlannerId){
        mealPlannerService.deleteMealPlanner(mealPlannerId);
    }

    @GetMapping("/planner/user/{userId}")
    @PreAuthorize("@mySecurityService.isUser(principal, #userId)")
    List<MealPlanner> getUserMealPlanners(@PathVariable String userId){
        return mealPlannerService.getUserMealPlanners(userId);
    }
}
