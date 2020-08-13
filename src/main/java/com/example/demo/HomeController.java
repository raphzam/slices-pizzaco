package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    TypeRepository typeRepository;

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
        model.addAttribute("doughs", ingredientRepository.findAllByType(typeRepository.findByName("dough")));
        model.addAttribute("proteins", ingredientRepository.findAllByType(typeRepository.findByName("protein")));
        model.addAttribute("veggies", ingredientRepository.findAllByType(typeRepository.findByName("veggie")));
        model.addAttribute("sauces", ingredientRepository.findAllByType(typeRepository.findByName("sauce")));
        model.addAttribute("toppings", ingredientRepository.findAllByType(typeRepository.findByName("topping")));
        //ingredients
        return "orderForm";
    }

    @RequestMapping("/updatepizza/{id}")
    public String updatePizza(@PathVariable("id") long id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());

        model.addAttribute("doughs", ingredientRepository.findAllByType(typeRepository.findByName("dough")));
        model.addAttribute("proteins", ingredientRepository.findAllByType(typeRepository.findByName("protein")));
        model.addAttribute("veggies", ingredientRepository.findAllByType(typeRepository.findByName("veggie")));
        model.addAttribute("sauces", ingredientRepository.findAllByType(typeRepository.findByName("sauce")));
        model.addAttribute("toppings", ingredientRepository.findAllByType(typeRepository.findByName("topping")));

        return "orderForm";
    }

    @PostMapping("/processOrder")
    public String processPizzaOrder(@ModelAttribute("pizza") Pizza pizza, Model model, Principal principal) {

        if (!pizzaRepository.existsById(pizza.getId())) {

            Order order = new Order();
            order.addPizza(pizza);
            pizza.setPrice(pizza.calculatePrice());
            pizza.setOrder(order);
            order.setTotal(order.calculateTotal());

            if (principal != null) {
                User currentUser = userRepository.findByUsername(principal.getName());
                order.setUser(currentUser);
//            order.setComplete(true);
            }
            orderRepository.save(order);
        } else {
//            Order order = pizza.getOrder();
            pizza.setPrice(pizza.calculatePrice());
            pizzaRepository.save(pizza);
//            order.setTotal(order.calculateTotal());
//            orderRepository.save(order);
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String loadUserCart(Model model, Principal principal) {
        for (Order order : orderRepository.findAll()) {
            order.setTotal(order.calculateTotal());
            orderRepository.save(order);
        }

        if (principal != null) {
            User currentUser = userRepository.findByUsername(principal.getName());
            for (Order order : orderRepository.findAllByUserNull()) {
                order.setUser(currentUser);
                orderRepository.save(order);
            }
            model.addAttribute("user", currentUser);
        } else {
//            model.addAttribute("guestOrders",orderRepository.findAllByComplete(false));
            model.addAttribute("guestOrders", orderRepository.findAllByUserNull());
        }
        return "cart";
    }

    @RequestMapping("/addToOrder/{id}")
    public String addToOrder(@PathVariable("id") long orderID, Model model) {
//        model.addAttribute("order", orderRepository.findById(orderID).get());

        Pizza pizza = new Pizza();
        pizza.setOrder(orderRepository.findById(orderID).get());

        model.addAttribute("pizza", pizza);

        model.addAttribute("doughs", ingredientRepository.findAllByType(typeRepository.findByName("dough")));
        model.addAttribute("proteins", ingredientRepository.findAllByType(typeRepository.findByName("protein")));
        model.addAttribute("veggies", ingredientRepository.findAllByType(typeRepository.findByName("veggie")));
        model.addAttribute("sauces", ingredientRepository.findAllByType(typeRepository.findByName("sauce")));
        model.addAttribute("toppings", ingredientRepository.findAllByType(typeRepository.findByName("topping")));

        return "additionalpizza";
    }

    @RequestMapping("/processAdditional")
    public String processAdditionalPizza(@ModelAttribute Pizza pizza, Model model, Principal principal) {
        Order order = pizza.getOrder();

        order.addPizza(pizza);
        pizza.setPrice(pizza.calculatePrice());
        pizza.setOrder(order);
//        order.setTotal(order.calculateTotal());
        orderRepository.save(order);

        return "redirect:/";
    }

    @RequestMapping("/checkout/{id}")
    public String userCheckout(@PathVariable("id") long id, Model model, Principal principal) {
        Order order = orderRepository.findById(id).get();

        order.setComplete(true);
        order.setLocalTime();
        orderRepository.save(order);
        User currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("checkoutMessage", "Your order has been submitted!");
        return "cart";
    }
}


