package com.example.backend.model;

import com.example.backend.Meal;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mealplanner")
public class MealPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_planner_id")
    private Long id;

    private String userId;

    private String name;

    @OneToMany(mappedBy = "mealPlanner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Meal> meals;

    public MealPlanner() {
    }

    public MealPlanner(String userId, String name){
        this.userId = userId;
        this.name = name;
        this.meals = new HashSet<>();
    }

    public MealPlanner(String userId, String name, Set<Meal> meals) {
        this.userId = userId;
        this.meals = meals;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealPlanner that = (MealPlanner) o;
        return id .equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MealPlanner{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", meals=" + meals +
                '}';
    }
}
