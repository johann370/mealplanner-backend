package com.example.backend.service;

import com.example.backend.model.Meal;
import com.example.backend.repository.MealRepository;
import com.example.backend.dto.CalendarDTO;
import com.example.backend.enums.DayOfWeek;
import com.example.backend.enums.MealType;
import com.example.backend.exception.ObjectNotFoundException;
import com.example.backend.model.MealPlanner;
import com.example.backend.repository.MealPlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlannerServiceImpl implements MealPlannerService {
    @Autowired
    private MealPlannerRepository mealPlannerRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public MealPlanner getMealPlanner(Long mealPlannerId) {
        Optional<MealPlanner> mealPlanner = mealPlannerRepository.findById(mealPlannerId);
        if(mealPlanner.isEmpty()){
            throw new ObjectNotFoundException("Could not find meal planner with id " + mealPlannerId);
        }
        return mealPlanner.get();
    }

    @Override
    public void deleteMealPlanner(Long mealPlannerId) {
        Optional<MealPlanner> mealPlanner = mealPlannerRepository.findById(mealPlannerId);
        if(mealPlanner.isEmpty()){
            throw new ObjectNotFoundException("Could not find meal planner with id " + mealPlannerId);
        }
        mealPlannerRepository.deleteById(mealPlannerId);
    }

    @Override
    public MealPlanner createMealPlanner(String userId, String plannerName) {
        MealPlanner mealPlanner = new MealPlanner(userId, plannerName);
        for(DayOfWeek day : DayOfWeek.values()){
            for(MealType type : MealType.values()){
                Meal meal = mealRepository.save(new Meal(day, type, mealPlanner));
                mealPlanner.addMeal(meal);
            }
        }
        return mealPlannerRepository.save(mealPlanner);
    }

    @Override
    public List<MealPlanner> getUserMealPlanners(String userId) {
        return mealPlannerRepository.getMealPlannersByUserId(userId);
    }

    public CalendarDTO mealPlannerToCalendar(MealPlanner mealPlanner) {
        return new CalendarDTO(mealPlanner.getMeals());
    }
}
