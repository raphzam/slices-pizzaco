package com.example.demo.bootstrap;

import com.example.demo.authentication.Role;
import com.example.demo.authentication.RoleRepository;
import com.example.demo.authentication.User;
import com.example.demo.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader extends CommandLineRunnerBean {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    public void run (String...args){
        User user2 = new User();
        user2.setFirstName("Olga");
        user2.setLastName("G.");
        user2.setEmail("pizzaconfirmationjava@gmail.com");
        user2.setPhoneNumber("555-123-4444");
        user2.setUsername("olga");
        user2.setPassword("password");
        user2.setEnabled(true);;
        userRepository.save(user2);

        Role role2 = new Role("olga", "ROLE_USER");
        roleRepository.save(role2);

        User user3 = new User();
        user3.setFirstName("Raphael");
        user3.setLastName("Z.");
        user3.setEmail("pizzaconfirmationjava@gmail.com");
        user3.setPhoneNumber("555-123-5555");
        user3.setUsername("raphael");
        user3.setPassword("password");
        user3.setEnabled(true);;
        userRepository.save(user3);

        Role role3 = new Role("raphael", "ROLE_USER");
        roleRepository.save(role3);

        User user4 = new User();
        user4.setFirstName("Ingrid");
        user4.setLastName("M.");
        user4.setEmail("pizzaconfirmationjava@gmail.com");
        user4.setPhoneNumber("555-123-6666");
        user4.setUsername("ingrid");
        user4.setPassword("password");
        user4.setEnabled(true);;
        userRepository.save(user4);

        Role role4 = new Role("ingrid", "ROLE_USER");
        roleRepository.save(role4);

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("pizzaconfirmationjava@gmail.com");
        admin.setPhoneNumber("555-123-7777");
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setEnabled(true);;
        userRepository.save(admin);

        Role roleAdmin = new Role("admin","ROLE_ADMIN");
        roleRepository.save(roleAdmin);

//        System.out.println(userRepository.findAllByFirstNameContainingOrLastNameContaining("m","m"));
//        System.out.println(userRepository.findByLastNameContainingOrFirstNameContainingAllIgnoreCase("m","m"));

    }
}
