package com.example.backend.service;

import com.example.backend.model.Meal;
import com.example.backend.repository.MealRepository;
import com.example.backend.enums.DayOfWeek;
import com.example.backend.enums.MealType;
import com.example.backend.exception.ObjectNotFoundException;
import com.example.backend.model.Planner;
import com.example.backend.repository.PlannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlannerServiceImpl implements PlannerService {
    @Autowired
    private PlannerRepository plannerRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Planner getPlannerById(Long plannerId) {
        Optional<Planner> planner = plannerRepository.findById(plannerId);
        if(planner.isEmpty()){
            throw new ObjectNotFoundException("Could not find planner with id " + plannerId);
        }
        return planner.get();
    }

    @Override
    public void deletePlannerById(Long plannerId) {
        Optional<Planner> planner = plannerRepository.findById(plannerId);
        if(planner.isEmpty()){
            throw new ObjectNotFoundException("Could not find planner with id " + plannerId);
        }
        plannerRepository.deleteById(plannerId);
    }

    @Override
    public Planner createPlanner(String userId, String plannerName) {
        Planner planner = new Planner(userId, plannerName);
        createPlannerMeals(planner);
        return plannerRepository.save(planner);
    }

    @Override
    public List<Planner> getUserPlanners(String userId) {
        return plannerRepository.getPlannersByUserId(userId);
    }

    private void createPlannerMeals(Planner planner){
        for(DayOfWeek day : DayOfWeek.values()){
            for(MealType type : MealType.values()){
                Meal meal = mealRepository.save(new Meal(day, type, planner));
                planner.addMeal(meal);
            }
        }
    }
}
