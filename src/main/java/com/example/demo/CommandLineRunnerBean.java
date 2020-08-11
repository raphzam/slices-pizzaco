package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;


@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public void run(String... args) {

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("password");
        user1.setEnabled(true);;
        userRepository.save(user1);

        //DOUGH

        Ingredient ing1 = new Ingredient();
        ing1.setName("NY Style");
        ingredientRepository.save(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setName("Neapolitan");
        ingredientRepository.save(ing2);

//        SAUCE

        Ingredient ing3 = new Ingredient();
        ing3.setName(" Classic Tomato");
        ingredientRepository.save(ing3);

        Ingredient ing4 = new Ingredient();
        ing4.setName("White Sauce");
        ingredientRepository.save(ing4);

//        PROTEIN

        Ingredient ing5 = new Ingredient();
        ing5.setName("Chicken Dices");
        ingredientRepository.save(ing5);

        Ingredient ing6 = new Ingredient();
        ing6.setName("Pepperoni");
        ingredientRepository.save(ing6);

//        VEGETABLE

        Ingredient ing7 = new Ingredient();
        ing7.setName("Bell pepper");
        ingredientRepository.save(ing7);

        Ingredient ing8 = new Ingredient();
        ing8.setName("Mushroom");
        ingredientRepository.save(ing8);

//        ADDITIONAL TOPPINGS

        Ingredient ing9 = new Ingredient();
        ing9.setName("Pineapple");
        ingredientRepository.save(ing9);

        Ingredient ing10 = new Ingredient();
        ing10.setName("Black Olives");
        ingredientRepository.save(ing10);

    }

}
