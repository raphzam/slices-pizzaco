package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza_table")
public class Pizza {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private Set<Ingredient> ingredients;

    @ManyToOne
    private PizzaOrder pizzaOrder;

    public Pizza() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        if (this.ingredients==null){
            this.ingredients=new HashSet<Ingredient>();
        }
        this.ingredients.add(ingredient);
    }

    public PizzaOrder getOrder() {
        return pizzaOrder;
    }

    public void setOrder(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }
}
