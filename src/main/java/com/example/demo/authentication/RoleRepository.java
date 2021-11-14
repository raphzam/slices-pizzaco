package com.example.demo.authentication;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Iterable<Role> findAllByRole(String role);
}

