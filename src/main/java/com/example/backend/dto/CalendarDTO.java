package com.example.backend.dto;

import com.example.backend.Meal;
import com.example.backend.Recipe;
import com.example.backend.enums.DayOfWeek;
import com.example.backend.enums.MealType;

import java.util.*;

public class CalendarDTO {
    //TODO Implement CalendarDTO
    public EnumMap<DayOfWeek, EnumMap<MealType, Set<Recipe>>> calendar;

    public CalendarDTO(Set<Meal> meals) {
        this.calendar = new EnumMap<>(DayOfWeek.class);
        EnumMap<MealType, Set<Recipe>> temp = new EnumMap<>(MealType.class);

        for(Meal meal: meals) {
            temp.put(meal.getMealType(), meal.getRecipes());
            calendar.put(meal.getMealDay(), temp.clone());
        }
    }
}
