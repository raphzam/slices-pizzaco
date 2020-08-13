package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    //    Iterable <User> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastname);
    Iterable<User> findByLastNameContainingOrFirstNameContainingAllIgnoreCase(String lastname, String firstname);


}

