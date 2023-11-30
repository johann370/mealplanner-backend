package com.example.backend;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long id;
    private String mealDay;
    private String mealType;

    @ManyToMany
            @JoinTable(
                    name = "meal_recipe",
                    joinColumns = @JoinColumn(name = "meal_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id")
            )
    Set<Recipe> recipes;

    public Meal(){}

    public Meal(String mealDay, String mealType) {
        this.mealDay = mealDay;
        this.mealType = mealType;
        this.recipes = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getMealDay() {
        return mealDay;
    }

    public void setMealDay(String mealDay) {
        this.mealDay = mealDay;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe newRecipe) {
        this.recipes.add(newRecipe);
    }

    public void deleteRecipe(Long id) {
        this.recipes.removeIf(recipe -> recipe.getId().equals(id));
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", day=" + mealDay +
                ", type='" + mealType + '\'' +
                ", recipes=" + recipes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id.equals(meal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
