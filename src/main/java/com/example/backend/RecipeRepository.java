package com.example.backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    boolean existsByNameAndUrl(String name, String url);
}
