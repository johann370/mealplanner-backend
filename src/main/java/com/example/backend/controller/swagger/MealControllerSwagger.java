package com.example.backend.controller.swagger;

import com.example.backend.model.Meal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "meals", description = "Adding and removing recipes from meals in a planner")
public interface MealControllerSwagger {

    @Operation(summary = "Add recipe", description = "Adds recipes to a meal by taking in a meal id and recipe id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe added"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    Meal addRecipeToMeal(@Parameter(description = "ID of meal to add recipes to") Long mealId, @Parameter(description = "ID of recipe to add") Long recipeId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted recipe"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @Operation(summary = "Remove recipe", description = "Removes recipes from a meal by taking in a meal id and recipe id")
    void deleteRecipeFromMeal(@Parameter(description = "ID of meal to remove recipes from") Long mealId, @Parameter(description = "ID of recipe to remove") Long recipeId);
}
