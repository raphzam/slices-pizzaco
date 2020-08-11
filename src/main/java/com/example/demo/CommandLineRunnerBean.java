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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public void run (String...args){

        User user1 = new User("userPizza","password",true);
        userRepository.save(user1);

        Ingredient cheese = new Ingredient();
        cheese.setName("Mozzarella");
        ingredientRepository.save(cheese);

        Ingredient vegetable = new Ingredient();
        vegetable.setName("Green Pepper");
        ingredientRepository.save(vegetable);

        Ingredient protein = new Ingredient();
        protein.setName("Pepporoni");
        ingredientRepository.save(protein);

        Ingredient dough = new Ingredient();
        dough.setName("Gluten-Free");
        ingredientRepository.save(dough);


        Order order1 = new Order();

        Pizza pizza1 = new Pizza();
        pizza1.addIngredient(cheese);
        pizza1.addIngredient(vegetable);

        order1.setUser(user1);
        orderRepository.save(order1);
        pizza1.setOrder(order1);
        pizzaRepository.save(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.addIngredient(dough);
        pizza2.addIngredient(vegetable);
        pizza2.addIngredient(protein);
        pizza2.setOrder(order1);
        pizzaRepository.save(pizza2);

        Order order2 = new Order();
        order2.setUser(user1);
        orderRepository.save(order2);

    }

}
