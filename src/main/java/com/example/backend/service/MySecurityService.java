package com.example.backend.service;

import com.example.backend.repository.MealRepository;
import com.example.backend.model.Planner;
import com.example.backend.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class MySecurityService {

    @Autowired
    PlannerRepository plannerRepository;

    @Autowired
    MealRepository mealRepository;

    public boolean ownsPlanner(Jwt principal, Long plannerId) {

        return plannerRepository.findById(plannerId).get().getUserId().equals(getUserIdFromPrincipal(principal));
    }

    public boolean ownsMeal(Jwt principal, Long mealId) {
        Planner planner = mealRepository.findById(mealId).get().getPlanner();

        return planner.getUserId().equals(getUserIdFromPrincipal(principal));
    }

    public boolean isUser(Jwt principal, String userId) {
        return getUserIdFromPrincipal(principal).equals(userId);
    }

    private String getUserIdFromPrincipal(Jwt principal){
        return principal.getClaims().get("sub").toString().replace("|", "");
    }
}
