package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    private final RecipeRepository repository;

    RecipeController(RecipeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/recipes")
    List<Recipe> getRecipes() {
        return repository.findAll();
    }

    @GetMapping("/recipes/{id}")
    Recipe getRecipe(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @PostMapping("/recipes")
    Recipe addRecipe(@RequestBody Recipe newRecipe){
        return repository.save(newRecipe);
    }

    @DeleteMapping("/recipes/{id}")
    void deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
