package com.example.demo.authentication;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    //    Iterable <User> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastname);
    Iterable<User> findByLastNameContainingOrFirstNameContainingAllIgnoreCase(String lastname, String firstname);


}

