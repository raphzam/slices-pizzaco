package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "ingredient_table")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    private Set<Pizza> pizzas;


    private String picUrl;

    private boolean inStock;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    public Ingredient() {
        this.inStock=true;
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

<<<<<<< HEAD
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
=======
    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
>>>>>>> 6bb8957c7c26e9d261c5b677fad5e24b1ac975d7
    }
}
