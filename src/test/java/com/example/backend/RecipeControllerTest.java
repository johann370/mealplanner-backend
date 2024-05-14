package com.example.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @MockBean
    private RecipeRepository recipeRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldGetAllRecipes() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe(1L, "test name 1", "test url 1", "image url 1"));
        recipes.add(new Recipe(2L, "test name 2", "test url 2", "image url 2"));
        recipes.add(new Recipe(3L, "test name 3", "test url 3", "image url 3"));
        when(recipeRepository.findAll()).thenReturn(recipes);
        this.mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("test name 1"))
                .andExpect(jsonPath("$[0].url").value("test url 1"))
                .andExpect(jsonPath("$[0].imageUrl").value("image url 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("test name 2"))
                .andExpect(jsonPath("$[1].url").value("test url 2"))
                .andExpect(jsonPath("$[1].imageUrl").value("image url 2"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("test name 3"))
                .andExpect(jsonPath("$[2].url").value("test url 3"))
                .andExpect(jsonPath("$[2].imageUrl").value("image url 3"));
    }

    @Test
    void shouldGetRecipeById() throws Exception {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe(1L, "test recipe", "test url", "test image url")));
        this.mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test recipe"))
                .andExpect(jsonPath("$.url").value("test url"))
                .andExpect(jsonPath("$.imageUrl").value("test image url"));
    }

    @Test
    void shouldAddRecipes() throws Exception {
        Recipe recipe = new Recipe("test", "test url", "test image url");
        Recipe returnedRecipe = new Recipe( 1L, "test", "test url", "test image url");
        when(recipeRepository.save(recipe)).thenReturn(returnedRecipe);

        JSONObject json = new JSONObject();
        json.put("name", "test");
        json.put("url", "test url");
        this.mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(json.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.url").value("test url"))
                .andExpect(jsonPath("$.imageUrl").value("test image url"));
    }

    @Test
    void shouldNotAddDuplicateRecipe() throws Exception {
        Recipe recipe = new Recipe("test", "test url", "test image url");
        Recipe returnedRecipe = new Recipe( 1L, "test", "test url", "test image url");
        when(recipeRepository.existsByNameAndUrl(recipe.getName(), recipe.getUrl())).thenReturn(true);

        JSONObject json = new JSONObject();
        json.put("name", "test");
        json.put("url", "test url");
        json.put("imageUrl", "test image url");
        this.mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof DuplicateRecipeException))
                .andExpect(result -> assertEquals("Could not add recipe because it already exists", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldThrowExceptionOnRecipeNotFoundGet() throws Exception {
        when(recipeRepository.findById(1L)).thenThrow(new RecipeNotFoundException(1L));
        this.mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof RecipeNotFoundException))
                .andExpect(result -> assertEquals("Could not find recipe with id: 1", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldNotAddRecipeWhenInvalidRequestBody() throws Exception {
        JSONObject json = new JSONObject();
        json.put("invalid", "json");

        this.mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddRecipeWhenEmptyValues() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "test name");
        json.put("url", "");

        this.mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteRecipeById() throws Exception {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(new Recipe("test name", "test url", "test image url")));
        this.mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldThrowExceptionOnRecipeNotFoundDelete() throws Exception {
        when(recipeRepository.findById(1L)).thenThrow(new RecipeNotFoundException(1L));
        this.mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof RecipeNotFoundException))
                .andExpect(result -> assertEquals("Could not find recipe with id: 1", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldUpdateFromSheets() throws Exception {

    }


}
