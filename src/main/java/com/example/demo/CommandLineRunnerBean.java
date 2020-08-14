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
    TypeRepository typeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void run(String... args) {

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("password");
        user1.setEnabled(true);
        userRepository.save(user1);

        Role role = new Role("user", "ROLE_USER");
        roleRepository.save(role);


        //TYPES
        Type dough = new Type("dough");
        typeRepository.save(dough);

        Type cheese = new Type("cheese");
        typeRepository.save(cheese);

        Type sauce = new Type("sauce");
        typeRepository.save(sauce);

        Type veggie = new Type("veggie");
        typeRepository.save(veggie);

        Type protein = new Type("protein");
        typeRepository.save(protein);

        Type topping = new Type("topping");
        typeRepository.save(topping);


        //DOUGH

        Ingredient ing1 = new Ingredient();
        ing1.setName("NY Style Crust");
        ing1.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240832/crust_yedtop.jpg");
        ing1.setType(dough);
//        ing1.setTally(3);
        ingredientRepository.save(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setName("Neapolitan Crust");
        ing2.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240832/crust_yedtop.jpg");
        ing2.setType(dough);
        ingredientRepository.save(ing2);

//        CHEESE
        Ingredient cheese1 = new Ingredient();
//        cheese1.setPicUrl();
        cheese1.setName("Mozzarella");
        cheese1.setType(cheese);
//        cheese1.setTally(5);
        ingredientRepository.save(cheese1);

        Ingredient cheese2 = new Ingredient();
//        cheese1.setPicUrl();
        cheese2.setName("Provolone");
        cheese2.setType(cheese);
        ingredientRepository.save(cheese2);

//        SAUCE

        Ingredient ing3 = new Ingredient();
        ing3.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597241335/tomatosauce_mldjpj.png");
        ing3.setName("Classic Tomato");
        ing3.setType(sauce);
        ingredientRepository.save(ing3);

        Ingredient ing4 = new Ingredient();
        ing4.setName("White Sauce");
        ing4.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597241253/whitesauce_wjfc53.png");
        ing4.setType(sauce);
        ingredientRepository.save(ing4);

//        PROTEIN

        Ingredient ing5 = new Ingredient();
        ing5.setName("Chicken Dices");
        ing5.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240838/chicken_mmk9sd.jpg");
        ing5.setType(protein);
        ingredientRepository.save(ing5);

        Ingredient ing6 = new Ingredient();
        ing6.setName("Pepperoni");
        ing6.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240802/pepperonipng_bhowqr.png");
        ing6.setType(protein);
        ingredientRepository.save(ing6);

//        VEGETABLE

        Ingredient ing7 = new Ingredient();
        ing7.setName("Bell pepper");
        ing7.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240832/bellpeppers_mnydni.jpg");
        ing7.setType(veggie);
        ingredientRepository.save(ing7);

        Ingredient ing8 = new Ingredient();
        ing8.setName("Mushroom");
        ing8.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240846/mashroom_gkhwym.jpg");
        ing8.setType(veggie);
        ingredientRepository.save(ing8);

//        ADDITIONAL TOPPINGS

        Ingredient ing9 = new Ingredient();
        ing9.setName("Pineapple");
        ing9.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240823/pineapplepng_xedxzp.png");
        ing9.setType(topping);
        ingredientRepository.save(ing9);

        Ingredient ing10 = new Ingredient();
        ing10.setName("Black Olives");
        ing10.setPicUrl("https://res.cloudinary.com/cloudim/image/upload/v1597240842/blackolives_vbesoh.jpg");
        ing10.setType(topping);
//        ing10.setTally(7);
        ingredientRepository.save(ing10);

//        for (Ingredient ingredient : ingredientRepository.findAllByOrderByTallyDesc()){
//            System.out.println(ingredient.getName() + " " + ingredient.getTally());
//        }

    }

}
