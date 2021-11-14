package com.example.demo.repository;

import com.example.demo.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
    Type findByName(String name);
}
