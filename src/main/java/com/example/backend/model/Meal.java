package com.example.backend.model;

import com.example.backend.enums.DayOfWeek;
import com.example.backend.enums.MealType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private DayOfWeek mealDay;
    private MealType mealType;

    @ManyToMany
            @JoinTable(
                    name = "meal_recipe",
                    joinColumns = @JoinColumn(name = "meal_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id")
            )
    Set<Recipe> recipes;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="meal_planner_id")
    @JsonIgnore
    private MealPlanner mealPlanner;

    public Meal(){}

    public Meal(DayOfWeek mealDay, MealType mealType, MealPlanner mealPlanner) {
        this.mealDay = mealDay;
        this.mealType = mealType;
        this.recipes = new HashSet<>();
        this.mealPlanner = mealPlanner;
    }

    public Meal(DayOfWeek mealDay, MealType mealType) {
        this.mealDay = mealDay;
        this.mealType = mealType;
        this.recipes = new HashSet<>();
        this.mealPlanner = new MealPlanner();
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

    public MealPlanner getMealPlanner() {
        return mealPlanner;
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
