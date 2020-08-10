package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public void run (String...args){
        Ingredient cheese = new Ingredient();
        cheese.setName("Mozerella");
        ingredientRepository.save(cheese);

        Ingredient vegetable = new Ingredient();
        vegetable.setName("Green Pepper");
        ingredientRepository.save(vegetable);

        Pizza pizza1 = new Pizza();
        pizza1.addIngredient(cheese);
        pizza1.addIngredient(vegetable);
        pizzaRepository.save(pizza1);

    }

}
