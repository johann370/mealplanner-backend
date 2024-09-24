package com.example.backend.service;

import com.example.backend.exception.DuplicateObjectException;
import com.example.backend.exception.ObjectNotFoundException;
import com.example.backend.model.Recipe;
import com.example.backend.parsing.ParsedRecipe;
import com.example.backend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import static com.example.backend.parsing.RecipeParser.parseRecipe;
import static com.example.backend.service.SheetsService.getSheetsUrls;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> getRecipe(Long recipeId) {
        return Optional.of(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ObjectNotFoundException("Could not find recipe with id: " + recipeId)));
    }

    @Override
    public Recipe addRecipe(Recipe newRecipe) {
        if(recipeRepository.existsByNameAndUrl(newRecipe.getName(), newRecipe.getUrl())){
            throw new DuplicateObjectException("Could not add recipe because it already exists");
        }

        return recipeRepository.save(newRecipe);
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        if(recipeRepository.findById(recipeId).isEmpty()){
            throw new ObjectNotFoundException("Could not find recipe with id: " + recipeId);
        }

        recipeRepository.deleteById(recipeId);
    }

    @Override
    public void updateFromSheets() throws GeneralSecurityException, IOException {
        List<String> recipeUrls = getSheetsUrls();
        for (String url : recipeUrls) {
            ParsedRecipe parsedRecipe = parseRecipe(url);
            Recipe recipe = new Recipe(parsedRecipe.getTitle(), parsedRecipe.getUrl(), parsedRecipe.getImageUrl());
            if(!recipeRepository.existsByNameAndUrl(recipe.getName(), recipe.getUrl())){
                recipeRepository.save(recipe);
            }
        }
    }
}
