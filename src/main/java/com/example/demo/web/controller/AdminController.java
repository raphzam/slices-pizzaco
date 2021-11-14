package com.example.demo.web.controller;

import com.example.demo.config.CloudinaryConfiguration;
import com.example.demo.model.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.model.Pizza;
import com.example.demo.repository.PizzaRepository;
import com.example.demo.authentication.RoleRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
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
    CloudinaryConfiguration cloudinaryConfiguration;

    @RequestMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admindashboard";
    }

    @RequestMapping("/admin/ingredients")
    public String viewIngredients(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "allingredients";
    }

    @RequestMapping("/admin/customers")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", userRepository.findAll());
        return "customerByName";
    }

    @RequestMapping("/admin/topthree")
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

    @RequestMapping("/admin/totalsales")
    public String totalSales(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        double totalSales = 0;

        for (Order order : orderRepository.findAll()) {
            totalSales += order.getTotal();
        }

        model.addAttribute("totalSales", totalSales);
        return "totalsales";
    }

    @RequestMapping("/admin/addingredient")
    public String updateAndAddIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("type", typeRepository.findAll());
        return "updateingredient";
    }

    @PostMapping("/admin/processAddIngredient")
    public String processUpdateAndAddIngredient(@ModelAttribute Ingredient ingredient) {

//        , @RequestParam("file") MultipartFile file
//        if (file.isEmpty()) {
//            return "redirect:/admin/addingredient";
//        }
//        try {
//            Map uplaodResult = cloudinaryConfiguration.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
//            ingredient.setPicUrl(uplaodResult.get("url").toString());
//            ingredientRepository.save(ingredient);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/admin/addingredient";
//        }
        ingredientRepository.save(ingredient);
        return "redirect:/admin/ingredients";
    }

    @RequestMapping("/admin/updateIngredient/{id}")
    public String updateIngredient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("type", typeRepository.findAll());
        model.addAttribute("message", "New image required for update");
        return "updateingredient";
    }

    @RequestMapping("/admin/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable("id") long id) {
        ingredientRepository.deleteById(id);
        return "redirect:/admin/ingredients";
    }

    @RequestMapping("/admin/searchbyname")
    public String searchByFirstName(Model model) {
        ;
        return "searchbyname";
    }

}
