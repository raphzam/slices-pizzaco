package com.example.demo.model;

import com.example.demo.model.Ingredient;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany (mappedBy = "type",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @Override
    public String toString() {
        return name;
    }

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
