package com.example.demo;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza_table")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private Set<Ingredient> ingredients;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    private double price;

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

    public void addIngredient(Ingredient ingredient) {
        if (this.ingredients == null) {
            this.ingredients = new HashSet<Ingredient>();
        }
        this.ingredients.add(ingredient);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculatePrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        double pizzaPrice = 14.99;

        double extraCharge = 0; //5 ingredient base, charge for ingredients over 5
        if (ingredients.size() > 5) {
            extraCharge = ((ingredients.size() - 5) * .50);
        }
        pizzaPrice += extraCharge;



        return pizzaPrice;
    }



}
