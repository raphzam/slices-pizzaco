package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String orderPizza(Model model){
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        //ingredients
        return "orderForm";
    }

    @PostMapping("/processOrder")
    public String processPizzaOrder(@ModelAttribute("pizza") Pizza pizza, Model model){
        model.addAttribute("pizza", pizza);
        Order order = new Order();
        pizzaRepository.save(pizza);
        return "redirect:/";
    }








}
