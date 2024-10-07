package com.example.backend.model;

import com.example.backend.enums.DayOfWeek;
import com.example.backend.enums.MealType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "meal")
public class Meal {
    @Schema(example = "3")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long id;

    @Schema(example = "MONDAY")
    private DayOfWeek mealDay;

    @Schema(example = "DINNER")
    private MealType mealType;

    @ManyToMany
            @JoinTable(
                    name = "meal_recipe",
                    joinColumns = @JoinColumn(name = "meal_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id")
            )
    Set<Recipe> recipes;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="planner_id")
    @JsonIgnore
    private Planner planner;

    public Meal(){}

    public Meal(DayOfWeek mealDay, MealType mealType, Planner planner) {
        this.mealDay = mealDay;
        this.mealType = mealType;
        this.recipes = new HashSet<>();
        this.planner = planner;
    }

    public Meal(DayOfWeek mealDay, MealType mealType) {
        this.mealDay = mealDay;
        this.mealType = mealType;
        this.recipes = new HashSet<>();
        this.planner = new Planner();
    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getMealDay() {
        return mealDay;
    }

    public void setMealDay(DayOfWeek mealDay) {
        this.mealDay = mealDay;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
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

    public Planner getPlanner() {
        return planner;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", mealDay='" + mealDay + '\'' +
                ", mealType='" + mealType + '\'' +
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
