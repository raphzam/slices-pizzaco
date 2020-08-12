package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {


    Iterable<Order> findAllByComplete(Boolean complete);

    Iterable<Order> findAllByUserNull();
}
