package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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

    @Autowired
    private NotificationService notificationService;

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
        model.addAttribute("cheeses", ingredientRepository.findAllByType(typeRepository.findByName("cheese")));

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
        model.addAttribute("cheeses", ingredientRepository.findAllByType(typeRepository.findByName("cheese")));

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
        return "redirect:/cart";
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
        model.addAttribute("cheeses", ingredientRepository.findAllByType(typeRepository.findByName("cheese")));

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

        return "redirect:/cart";
    }

    @RequestMapping("/checkout/{id}")
    public String userCheckout(@PathVariable("id") long id, Model model, Principal principal) {
        Order order = orderRepository.findById(id).get();

        order.setComplete(true);
        order.setTimeSubmitted();
        orderRepository.save(order);
        User currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("checkoutMessage", "Your order has been submitted!");


        try{
            notificationService.sendNotification(currentUser);
        }catch (MailException e){

        }


        return "cart";
    }

    //SEARCH FUNCTION

    @PostMapping("/search")
    public String processSearch(@RequestParam("string") String name, Model model) {
//        model.addAttribute("customers", userRepository.findAllByFirstNameContainingOrLastNameContaining(name,name));
        model.addAttribute("customers", userRepository.findByLastNameContainingOrFirstNameContainingAllIgnoreCase(name, name));
        return "searchbyname";
    }


    @RequestMapping("/top3")
    public String top3() {
//        int nyCount = 0;
//        int neoCount = 0;
//        int mozzCount = 0;
//        int proCount = 0;
//        int redSauceCount = 0;
//        int whiteSauce = 0;
//        int chickenCount = 0;
//        int roniCount = 0;
//        int bellCount = 0;
//        int mushCount = 0;
//        int pineCount = 0;
//        int oliveCount = 0;
//        int miscCount = 0;
//        int i = (int) ingredient.getId();

//
//        switch (i) {
//            case 1:
//                nyCount++;
//                break;
//            case 2:
//                neoCount++;
//                break;
//            case 3:
//                mozzCount++;
//                break;
//            case 4:
//                proCount++;
//                break;
//            case 5:
//                redSauceCount++;
//                break;
//            case 6:
//                whiteSauce++;
//                break;
//            case 7:
//                chickenCount++;
//                break;
//            case 8:
//                roniCount++;
//                break;
//            case 9:
//                bellCount++;
//                break;
//            case 10:
//                mushCount++;
//                break;
//            case 11:
//                pineCount++;
//                break;
//            case 12:
//                oliveCount++;
//                break;
//            default:
//                miscCount++;
//                break;
//        }

        for (Pizza pizza : pizzaRepository.findAll()) {
            for (Ingredient ingredient : pizza.getIngredients()) {
                int i = ingredient.getTally();
                i++;
                ingredient.setTally(i);
                ingredientRepository.save(ingredient);
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/populartoppings")
    public String topThreeToppings(Model model) {
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            ingredient.setTally(0);
            ingredientRepository.save(ingredient);
        }

        //tally currently used ingredients
        for (Pizza pizza : pizzaRepository.findAll()) {
            for (Ingredient ingredient : pizza.getIngredients()) {
                int i = ingredient.getTally();
                i++;
                ingredient.setTally(i);
                ingredientRepository.save(ingredient);
            }
        }
        model.addAttribute("toppings", ingredientRepository.findAllByOrderByTallyDesc());
        return "topthreetoppings";
    }

    @RequestMapping("/admin/toggleIngredient/{id}")
    public String toggleIngredient(@PathVariable("id") long id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        boolean inStock = ingredient.isInStock();
        ingredient.setInStock(!inStock);
        ingredientRepository.save(ingredient);
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "allingredients";
    }


}


