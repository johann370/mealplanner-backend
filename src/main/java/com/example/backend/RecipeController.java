package com.example.backend;

import com.example.backend.parsing.ParsedRecipe;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import static com.example.backend.MealPlannerSheets.getSheetsUrls;
import static com.example.backend.parsing.RecipeParser.parseRecipe;

@RestController
@CrossOrigin(origins = {"http://jm-mealplanner.com.s3-website.us-east-2.amazonaws.com", "http://localhost:3000", "https://jm-mealplanner.xyz"})
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
    Optional<Recipe> getRecipe(@PathVariable Long id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id)));
    }

    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    Recipe addRecipe(@Valid @RequestBody Recipe newRecipe){
        if(repository.existsByNameAndUrl(newRecipe.getName(), newRecipe.getUrl())){
            throw new DuplicateRecipeException();
        }

        return repository.save(newRecipe);
    }

    @DeleteMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRecipe(@PathVariable Long id) {
        if(repository.findById(id).isEmpty()){
            throw new RecipeNotFoundException(id);
        }

        repository.deleteById(id);
    }

    @PutMapping ("/recipes/sheets")
    void updateFromSheets() throws GeneralSecurityException, IOException {
        List<String> recipeUrls = getSheetsUrls();
        for (String url : recipeUrls) {
            ParsedRecipe parsedRecipe = parseRecipe(url);
            Recipe recipe = new Recipe(parsedRecipe.getTitle(), parsedRecipe.getUrl(), parsedRecipe.getImageUrl());
            if(!repository.existsByNameAndUrl(recipe.getName(), recipe.getUrl())){
                repository.save(recipe);
            }
        }
    }
}
