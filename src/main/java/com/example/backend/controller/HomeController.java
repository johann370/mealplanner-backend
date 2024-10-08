package com.example.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
public class HomeController {

    @GetMapping("/")
    String home() {
        return "Meal Planner Backend";
    }
}
