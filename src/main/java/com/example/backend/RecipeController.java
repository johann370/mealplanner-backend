package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RecipeController {

    private final RecipeRepository repository;

    RecipeController(RecipeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/recipes")
    Iterable<Recipe> all() {
        return repository.findAll();
    }
}
