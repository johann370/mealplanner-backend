package com.example.backend.service;

import com.example.backend.MealRepository;
import com.example.backend.model.MealPlanner;
import com.example.backend.repository.MealPlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class MySecurityService {

    @Autowired
    MealPlannerRepository mealPlannerRepository;

    @Autowired
    MealRepository mealRepository;

    public boolean ownsMealPlanner(Jwt principal, Long mealPlannerId) {

        return mealPlannerRepository.findById(mealPlannerId).get().getUserId().equals(principal.getClaims().get("sub").toString().replace("|", ""));
    }

    public boolean ownsMeal(Jwt principal, Long mealId) {
        MealPlanner mealPlanner = mealRepository.findById(mealId).get().getMealPlanner();

        return mealPlanner.getUserId().equals(principal.getClaims().get("sub").toString().replace("|", ""));
    }

    public boolean isUser(Jwt principal, String userId) {
        return principal.getClaims().get("sub").toString().replace("|", "").equals(userId);
    }
}
