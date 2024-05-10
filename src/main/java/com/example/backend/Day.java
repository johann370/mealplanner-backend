package com.example.backend;

import java.util.Set;

public class Day {
    private Set<Recipe> breakfast, lunch, dinner;

    public Day(Set<Recipe> breakfast, Set<Recipe> lunch, Set<Recipe> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Day(MealRepository repository, String day){
        this.breakfast = repository.findMealsByMealDayAndMealType(day, "Breakfast").get(0).getRecipes();
        this.lunch = repository.findMealsByMealDayAndMealType(day, "Lunch").get(0).getRecipes();
        this.dinner = repository.findMealsByMealDayAndMealType(day, "Dinner").get(0).getRecipes();
    }

    public Set<Recipe> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Set<Recipe> breakfast) {
        this.breakfast = breakfast;
    }

    public Set<Recipe> getLunch() {
        return lunch;
    }

    public void setLunch(Set<Recipe> lunch) {
        this.lunch = lunch;
    }

    public Set<Recipe> getDinner() {
        return dinner;
    }

    public void setDinner(Set<Recipe> dinner) {
        this.dinner = dinner;
    }
}
