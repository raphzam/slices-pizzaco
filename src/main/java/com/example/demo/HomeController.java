package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/order")
    public String orderPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
//        model.addAttribute("ingredients", ingredientRepository.findAll());
//        model.addAttribute("dough", ingredientRepository.findByIdBetween(1, 2));
        model.addAttribute("doughs", ingredientRepository.findByIdBetween(1,2));
        model.addAttribute("sauces", ingredientRepository.findByIdBetween(3,4));
        model.addAttribute("toppings", ingredientRepository.findByIdBetween(5,10));
        //ingredients
        return "orderForm";
    }

    @PostMapping("/processOrder")
    public String processPizzaOrder(@ModelAttribute("pizza") Pizza pizza, Model model, @RequestParam("dough") long id) {
        Ingredient ing = ingredientRepository.findById(id).get();
        pizza.addIngredient(ing);

        model.addAttribute("pizza", pizza);
        Order order = new Order();
        order.addPizza(pizza);
        pizza.setPrice(pizza.calculatePrice());
        pizza.setOrder(order);
        order.setTotal(order.calculateTotal());
        orderRepository.save(order);
        return "redirect:/";
    }


}
