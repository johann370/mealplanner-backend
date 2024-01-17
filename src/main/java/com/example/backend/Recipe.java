package com.example.backend;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @NotBlank(message = "Recipe name must not be blank")
    private String name;

    @NotBlank(message = "Recipe url must not be blank")
    private String url;


    public Recipe(){}


    public Recipe(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Recipe(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return name.equals(recipe.name) && url.equals(recipe.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


}
