package com.example.backend.controller.swagger;

import com.example.backend.dto.PlannerDTO;
import com.example.backend.model.Planner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "planners", description = "Operations about planners")
public interface PlannerControllerSwagger {
    @Operation(summary = "Get planner", description = "Returns a planner by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Planner.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    Planner getPlannerById(@Parameter(description = "ID of planner to get") Long plannerId);

    @Operation(summary = "Create planner", description = "Creates a planner with the specified information in the request body. Belongs to the user with specified userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    Planner createPlanner(@Parameter(description = "ID of user") String userId, @RequestBody(description = "Planner to create") PlannerDTO plannerDTO);

    @Operation(summary = "Delete planner", description = "Deletes a planner by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    void deletePlannerById(@Parameter(description = "ID of planner to delete") Long plannerId);

    @Operation(summary = "Get user planners", description = "Lists all of a user's planners, specified by the userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    List<Planner> getUserPlanners(@Parameter(description = "ID of user") String userId);
}
