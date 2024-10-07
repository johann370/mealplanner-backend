package com.example.backend.controller;

import com.example.backend.controller.swagger.PlannerControllerSwagger;
import com.example.backend.dto.PlannerDTO;
import com.example.backend.model.Planner;
import com.example.backend.service.PlannerService;
import com.example.backend.service.MySecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
public class PlannerController implements PlannerControllerSwagger {

    private final PlannerService plannerService;
    private final MySecurityService mySecurityService;

    @Autowired
    PlannerController(PlannerService plannerService, MySecurityService mySecurityService){
        this.plannerService = plannerService;
        this.mySecurityService = mySecurityService;
    }


    @Override
    @GetMapping("/planner/{plannerId}")
    @PostAuthorize("@mySecurityService.ownsPlanner(principal, #plannerId)")
    public Planner getPlannerById(@PathVariable Long plannerId) {
        return plannerService.getPlannerById(plannerId);
    }

    @Override
    @PostMapping("/planner/user/{userId}")
    @PreAuthorize("@mySecurityService.isUser(principal, #userId)")
    public Planner createPlanner(@PathVariable String userId, @RequestBody PlannerDTO plannerDTO){
        return plannerService.createPlanner(userId, plannerDTO.plannerName());
    }

    @Override
    @DeleteMapping("/planner/{plannerId}")
    @PreAuthorize("@mySecurityService.ownsPlanner(principal, #plannerId)")
    public void deletePlannerById(@PathVariable Long plannerId){
        plannerService.deletePlannerById(plannerId);
    }


    @Override
    @GetMapping("/planner/user/{userId}")
    @PreAuthorize("@mySecurityService.isUser(principal, #userId)")
    public List<Planner> getUserPlanners(@PathVariable String userId){
        return plannerService.getUserPlanners(userId);
    }
}
