package com.example.demo.repository;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Iterable<Ingredient> findByIdBetween(long start, long end);

    Iterable<Ingredient> findAllByType(Type type);

    Iterable<Ingredient> findAllByOrderByTallyDesc();
}
