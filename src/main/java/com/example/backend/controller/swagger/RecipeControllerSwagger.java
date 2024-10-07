package com.example.backend.controller.swagger;

import com.example.backend.model.Recipe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;


@Tag(name = "recipes", description = "Recipes to be used in a planner")
public interface RecipeControllerSwagger {

    @Operation(summary = "List recipes", description = "List all recipes in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    List<Recipe> getRecipes();

    @Operation(summary = "Get recipe", description = "Get recipe by id, if recipe is not found throws a 404 error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recipe with the specified id was not found")
    })
    Optional<Recipe> getRecipeById(@Parameter(description = "ID of the recipe to get") @PathVariable Long recipeId);

    @Operation(summary = "Create recipe", description = "Add the recipe provided in the request body to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    Recipe createRecipe(@RequestBody(description = "The recipe that will be added to the database") Recipe newRecipe);

    @Operation(summary = "Delete recipe", description = "Delete a recipe by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    void deleteRecipe(@Parameter(description = "ID of the recipe to be deleted") @PathVariable Long recipeId);

    @Operation(summary = "Updates recipes from sheets", description = "Adds the recipes from a personal google sheets spreadsheet")
    void updateRecipesFromSheets() throws GeneralSecurityException, IOException;
}
