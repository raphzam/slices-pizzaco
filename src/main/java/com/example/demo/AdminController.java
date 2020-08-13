package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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
    public String adminDashboard(){
        return "admindashboard";
    }

    @RequestMapping("/admin/ingredients")
    public String viewIngredients(Model model){
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "allingredients";
    }

    @RequestMapping("/admin/customers")
    public String viewCustomers(Model model){
        model.addAttribute("customers", userRepository.findAll());
        return "customerByName";
    }

    @RequestMapping("/admin/topthree")
    public String topThreeToppings(){
        return "topthreetoppings";
    }

    @RequestMapping("/admin/totalsales")
    public String totalSales(Model model){
        model.addAttribute("orders", orderRepository.findAll());
        return "totalsales";
    }

    @RequestMapping("/admin/addingredient")
    public String updateAndAddIngredient(Model model){
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("type", typeRepository.findAll());
        return "updateingredient";
    }

    @PostMapping("/admin/processAddIngredient")
    public String processUpdateAndAddIngredient(@ModelAttribute Ingredient ingredient, @RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "redirect:/admin/addingredient";
        }
        try {
            Map uplaodResult = cloudinaryConfiguration.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            ingredient.setPicUrl(uplaodResult.get("url").toString());
            ingredientRepository.save(ingredient);
        }catch (IOException e){
            e.printStackTrace();
            return "redirect:/admin/addingredient";
        }
        return "redirect:/admin/ingredients";
    }

    @RequestMapping("/admin/updateIngredient/{id}")
    public String updateIngredient(@PathVariable("id") Long id, Model model){
        model.addAttribute("ingredient",ingredientRepository.findById(id).get());
        model.addAttribute("type", typeRepository.findAll());
        model.addAttribute("message", "New image required for update");
        return "updateingredient";
    }

    @RequestMapping("/admin/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable("id") long id){
        ingredientRepository.deleteById(id);
        return "redirect:/admin/ingredients";
    }

    @RequestMapping("/admin/searchbyname")
    public String searchByFirstName(Model model){ ;
        return "searchbyname";
    }

}
