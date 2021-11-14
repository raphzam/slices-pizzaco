package com.example.demo.web.controller;

import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PizzaRepository;
import com.example.demo.authentication.Role;
import com.example.demo.authentication.RoleRepository;
import com.example.demo.authentication.User;
import com.example.demo.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginRegistrationController {
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

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/processRegister")
    public String processUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", user);
        user.setEnabled(true);
        userRepository.save(user);

        Role role = new Role(user.getUsername(), "ROLE_USER");
        roleRepository.save(role);
        return "redirect:/login";
    }

}
