package com.example.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "planner")
public class Planner {
    @Schema(example = "321")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_id")
    private Long id;

    @Schema(example = "382018321")
    private String userId;

    @Schema(example = "My planner")
    private String name;

    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Meal> meals;

    public Planner() {
    }

    public Planner(String userId, String name){
        this.userId = userId;
        this.name = name;
        this.meals = new HashSet<>();
    }

    public Planner(String userId, String name, Set<Meal> meals) {
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
        Planner that = (Planner) o;
        return id .equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Planner{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", meals=" + meals +
                '}';
    }
}
