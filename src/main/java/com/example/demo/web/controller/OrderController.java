package com.example.demo.web.controller;

import com.example.demo.config.NotificationService;
import com.example.demo.authentication.User;
import com.example.demo.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/orderConfirmation")
    public String orderConfirmation (){
        return "Your order has been submitted";
    }

    @RequestMapping("/confirmation-success")
    public String confirmationSuccess(){
        User user7 = new User();
        user7.setFirstName("Test");
        user7.setLastName("Admin");
        user7.setEmail("pizzaconfirmationjava@gmail.com");
        user7.setPhoneNumber("555-123-4910");
        user7.setUsername("test");
        user7.setPassword("password");
        user7.setEnabled(true);;
        userRepository.save(user7);

        try{
            notificationService.sendNotification(user7);
        }catch (MailException e){

        }
        return "Thank you, e-mail confirmation sent";

    }

}
