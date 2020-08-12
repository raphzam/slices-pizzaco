package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Iterable<Ingredient> findByIdBetween(long start, long end);

    Iterable<Ingredient> findAllByType(Type type);
}
