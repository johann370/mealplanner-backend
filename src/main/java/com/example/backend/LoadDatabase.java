package com.example.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RecipeRepository repository, MealRepository mealRepository) {

        Recipe recipe = new Recipe("Recipe", "url");
        Recipe chicken = new Recipe("Chicken", "chicken-url");
        Recipe alfredo = new Recipe("Alfredo", "alfredo.com");

        repository.save(recipe);
        repository.save(chicken);
        repository.save(alfredo);

        Meal mondayBreakfast = new Meal("Monday", "Breakfast");
        Meal mondayLunch = new Meal("Monday", "Lunch");
        Meal mondayDinner = new Meal("Monday", "Dinner");
        Meal tuesdayBreakfast = new Meal("Tuesday", "Breakfast");
        Meal tuesdayLunch = new Meal("Tuesday", "Lunch");
        Meal tuesdayDinner = new Meal("Tuesday", "Dinner");
        Meal wednesdayBreakfast = new Meal("Wednesday", "Breakfast");
        Meal wednesdayLunch = new Meal("Wednesday", "Lunch");
        Meal wednesdayDinner = new Meal("Wednesday", "Dinner");
        Meal thursdayBreakfast = new Meal("Thursday", "Breakfast");
        Meal thursdayLunch = new Meal("Thursday", "Lunch");
        Meal thursdayDinner = new Meal("Thursday", "Dinner");
        Meal fridayBreakfast = new Meal("Friday", "Breakfast");
        Meal fridayLunch = new Meal("Friday", "Lunch");
        Meal fridayDinner = new Meal("Friday", "Dinner");
        Meal saturdayBreakfast = new Meal("Saturday", "Breakfast");
        Meal saturdayLunch = new Meal("Saturday", "Lunch");
        Meal saturdayDinner = new Meal("Saturday", "Dinner");
        Meal sundayBreakfast = new Meal("Sunday", "Breakfast");
        Meal sundayLunch = new Meal("Sunday", "Lunch");
        Meal sundayDinner = new Meal("Sunday", "Dinner");

        mondayLunch.addRecipe(chicken);
        thursdayBreakfast.addRecipe(recipe);
        sundayDinner.addRecipe(alfredo);
        sundayDinner.addRecipe(chicken);

        mealRepository.save(mondayBreakfast);
        mealRepository.save(mondayLunch);
        mealRepository.save(mondayDinner);
        mealRepository.save(tuesdayBreakfast);
        mealRepository.save(tuesdayLunch);
        mealRepository.save(tuesdayDinner);
        mealRepository.save(wednesdayBreakfast);
        mealRepository.save(wednesdayLunch);
        mealRepository.save(wednesdayDinner);
        mealRepository.save(thursdayBreakfast);
        mealRepository.save(thursdayLunch);
        mealRepository.save(thursdayDinner);
        mealRepository.save(fridayBreakfast);
        mealRepository.save(fridayLunch);
        mealRepository.save(fridayDinner);
        mealRepository.save(saturdayBreakfast);
        mealRepository.save(saturdayLunch);
        mealRepository.save(saturdayDinner);
        mealRepository.save(sundayBreakfast);
        mealRepository.save(sundayLunch);
        mealRepository.save(sundayDinner);

        Meal meal = new Meal("Monday", "Lunch");
        meal.addRecipe(recipe);
        mealRepository.save(new Meal("Monday", "Breakfast"));

        return args -> {
            log.info("Preloading " + repository.save(new Recipe("ONE POT CREAMY CAJUN CHICKEN PASTA", "https://www.budgetbytes.com/one-pot-creamy-cajun-chicken-pasta/")));
            log.info("Preloading " + repository.save(new Recipe("EASY LEMON PEPPER CHICKEN", "https://www.budgetbytes.com/easy-lemon-pepper-chicken/")));
            log.info("Preloading " + repository.save(recipe));
            log.info("Preloading " + mealRepository.save(meal));
        };
    }
}
